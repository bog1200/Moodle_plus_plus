import {redirect} from "next/navigation";
import {auth, signOut} from "@/auth";

export default async function Dashboard() {

    const session = await auth();

    if (!session) {
        return redirect("/")
    }

    const user = session.user!;

    return (
        <div>
            <h1>Dashboard</h1>
            <h2>Welcome, {user.name}!</h2>

            <form  action={async () => {
                "use server"
                return await signOut({redirectTo: "/"});


           }}>
                <button type="submit">Sign out</button>
            </form>
        </div>
    )
}