import NextAuth, { type DefaultSession} from "next-auth";
import { prisma } from "@/prisma"
import GitHub from "next-auth/providers/github"
import {PrismaAdapter} from "@auth/prisma-adapter";

declare module "next-auth" {
    /**
     * Returned by `auth`, `useSession`, `getSession` and received as a prop on the `SessionProvider` React Context
     */
    interface Session {
        user: {
           familyName: string;
           givenName: string;

           provider: string;
           address?: string;
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
            signIn: async ({ user, profile }) => {
              if (user && profile) {
                  await prisma.user.update({
                      where: { email: user.email! },
                      data: {
                          name: profile.name,
                          dob: profile.birthdate,
                          address: profile.address?.formatted || profile.address as string,
                          gender: profile.gender,
                      },
                  });
              }
                return true;
            },
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
            return {
                ...session,
                user: {
                    ...session.user,
                    id: token.sub,
                    familyName: token.family_name,
                    givenName: token.given_name,
                    provider: token.provider,
                    address: token.address,
                    gender: token.gender
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
                    email: profile.email,
                    dob: profile.birthdate,
                    address: profile.address,
                    gender: profile.gender
                };
            },
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
                    email: profile.email,
                    dob: profile.birthdate,
                    address: profile.address,
                    gender: profile.gender
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
                    email: profile.email,
                    dob: profile.birthdate,
                    address: profile.address,
                    gender: profile.gender
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

