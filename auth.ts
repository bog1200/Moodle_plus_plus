import NextAuth from "next-auth";

export const {
    handlers: { GET, POST },
    auth,
    signIn,
    signOut,
} = NextAuth({
    session: {
        strategy: 'jwt',
    },
    callbacks: {
        session: async ({ session, token }) => {
            if (session?.user) {
                if (token.sub != null) {
                    session.user.id = token.sub;
                }
            }
            return session;
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
    ],
});

