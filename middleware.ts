export { auth as middleware } from "@/auth"

import { auth } from "@/auth"

export default auth((req) => {
    if (!req.auth && req.nextUrl.pathname !== "/api/auth/signin") {
        const newUrl = new URL("/api/auth/signin", req.nextUrl.origin)
        return Response.redirect(newUrl)
    }
})
export const config = {
    matcher: ["/((?!api|sw.js|vercel.svg|_next/static|_next/image|favicon.ico).*)"],
}