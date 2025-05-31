import NextAuth, { type DefaultSession} from "next-auth";
import { PrismaAdapter } from "@auth/prisma-adapter"
import { prisma } from "@/prisma"
import GitHub from "next-auth/providers/github"

declare module "next-auth" {
    /**
     * Returned by `auth`, `useSession`, `getSession` and received as a prop on the `SessionProvider` React Context
     */
    interface Session {
        user: {
           familyName: string;
           givenName: string;

           provider: string;
            /**
             * By default, TypeScript merges new interface properties and overwrites existing ones.
             * In this case, the default session user properties will be overwritten,
             * with the new ones defined above. To keep the default session user properties,
             * you need to add them back into the newly declared interface.
             */
        } & DefaultSession["user"]
    }
}
export const {
    handlers: { GET, POST },
    auth,
    signIn,
    signOut,
} = NextAuth({
    debug: true,
    session: {
        strategy: 'jwt',
    },
    adapter: PrismaAdapter(prisma),
    callbacks:
        {
            jwt: async ({ token, account, profile }) => {

                if (account && profile) {
                    token.sub = account.provider === "github" ? profile.id!.toString() : profile.sub!;
                    token.provider = account.provider;
                }
                if (profile) {
                    console.log("P:"+profile.address, "T:"+token.address, "B:"+token.birthdate);
                    token.family_name = profile.family_name;
                    token.given_name = profile.given_name;
                    if (profile.address && profile.birthdate && profile.gender){
                        token.address = profile.address;
                        token.birthdate = profile.birthdate;
                        token.gender = profile.gender
                    }

                }

                return token;
            },
        session: async ({ session, token }) => {
            if (token.address && token.birthdate && token.gender) {
                const account = await prisma.account.findFirst({
                    where: {
                            provider: token.provider!,
                            providerAccountId: token.sub!,
                    },
                    select: {
                        userId: true,
                    },
                });

                if (account?.userId) {
                    await prisma.user.update({
                        where: { id: account.userId },
                        data: {
                            address: token.address,
                            dob: token.birthdate,
                            gender: token.gender,
                        },
                    });
                }
            }
            return {
                ...session,
                user: {
                    ...session.user,
                    id: token.sub,
                    familyName: token.family_name,
                    givenName: token.given_name,
                    provider: token.provider,
                },
            }
        },
        authorized: async ({ auth }) => {
            // Logged-in users are authenticated, otherwise redirect to login page
            return !!auth
        },
    },
    providers: [
        {
            id: "romailsso",
            name: "Romail SSO",
            style: {
                logo: "https://romail.app/favicon.ico",
            },
            type: "oauth",
            authorization: "https://auth.romail.app/oauth2/authorize",
            token: "https://auth.romail.app/oauth2/token",
            userinfo: "https://auth.romail.app/oauth2/userinfo",
            checks: ["pkce", "state"],
            clientId: process.env.AUTH_ROMAILSSO_ID,
            clientSecret: process.env.AUTH_ROMAILSSO_SECRET,
            profile: (profile) => {
                return {
                    id: profile.sub,
                    name: profile.name,
                    email: profile.email
                };
            }
        },
        {
            id: "bananaidp",
            name: "Banana SSO",
            style: {
                logo: "https://romail.app/favicon.ico",
            },
            type: "oidc",
            issuer: "https://banana-node.romail.app",
            authorization: {
                url: "https://banana-node.romail.app/oauth2/authorize",
                params: { scope: "openid profile email address" }
            },
            token: "https://banana-node.romail.app/oauth2/token",
            checks: ["state"],

            clientId: process.env.AUTH_BANANAIDP_ID,
            clientSecret: process.env.AUTH_BANANAIDP_SECRET,
            allowDangerousEmailAccountLinking: true,
            profile: (profile) => {
                return {
                    id: profile.sub,
                    name: profile.name,
                    email: profile.email
                };
            }
        },
        {
            id: "orangeidp",
            name: "Orange SSO",
            style: {
                logo: "https://romail.app/favicon.ico",
            },
            type: "oidc",
            issuer: "https://orange-node.romail.app",
            authorization: {
                url: "https://orange-node.romail.app/oauth2/authorize",
                params: { scope: "openid profile email address" }
            },
            token: "https://orange-node.romail.app/oauth2/token",
            checks: ["state"],

            clientId: process.env.AUTH_ORANGEIDP_ID,
            clientSecret: process.env.AUTH_ORANGEIDP_SECRET,
            allowDangerousEmailAccountLinking: true,
            profile: (profile) => {
                return {
                    id: profile.sub,
                    name: profile.name,
                    email: profile.email
                };
            }
        },

        GitHub({
            clientId: process.env.GITHUB_ID!,
            clientSecret: process.env.GITHUB_SECRET!,
            allowDangerousEmailAccountLinking: true,
        }),
    ],
});

