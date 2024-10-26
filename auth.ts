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
        authorized: async ({ auth }) => {
            // Logged in users are authenticated, otherwise redirect to login page
            return !!auth
        },
    },
    providers: [
        // {
        //     id: 'romailsso',
        //     name: 'Romail SSO',
        //     type: "oauth",
        //     clientId: "romailapp.mpp1",
        //     clientSecret: "sthQvelBSnWlSfXeRFbdlvwW37Gm9yUB",
        //     authorization: {
        //         url: "https://auth.romail.app/oauth2/authorize",
        //         params: {
        //             client_id: 'romailapp.mpp1',
        //         }
        //     },
        //     token: {
        //         url: "https://auth.romail.app/oauth2/token",
        //         body: {
        //             client_id: "romailapp.mpp1",
        //             client_secret: "sthQvelBSnWlSfXeRFbdlvwW37Gm9yUB",
        //         }
        //     },
        //     userinfo: "https://auth.romail.app/oauth2/userinfo",
        //
        //     profile: (profile) => {
        //         return {
        //             id: profile.sub,
        //             name: profile.name,
        //             email: profile.email,
        //         }
        //     }
        // },
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
            checks: ["state"],
            clientId: process.env.AUTH_ROMAILSSO_ID,
            clientSecret: process.env.AUTH_ROMAILSSO_SECRET,
        },
    ],
});

