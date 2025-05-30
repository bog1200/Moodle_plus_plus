
import { auth } from "@/auth"

export default auth((req) => {
    if (!req.auth && ( req.nextUrl.pathname !== "/api/auth/signin" && req.nextUrl.pathname !== "")) {
        const newUrl = new URL("/", req.nextUrl.origin)
        return Response.redirect(newUrl)
    }
})
export const config = {
    matcher: ["/((?!$|api|login|_next/static|_next/image|favicon.ico|logo.svg).*)"],
}