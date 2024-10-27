import NextAuth from "next-auth";

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
             client: { token_endpoint_auth_method: "client_secret_post" },
            authorization: "https://auth.romail.app/oauth2/authorize",
            token: "https://auth.romail.app/oauth2/token",
            userinfo: "https://auth.romail.app/oauth2/userinfo",
            checks: ["none"],
            clientId: process.env.AUTH_ROMAILSSO_ID,
            clientSecret: process.env.AUTH_ROMAILSSO_SECRET,
        },
    ],
});

