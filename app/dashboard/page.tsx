import {auth, signOut} from "@/auth";

export default async function Dashboard() {

    const session = await auth();

    const user = session!.user!;

    return (
        <div>
            <h1>Dashboard</h1>
            <h2>Welcome, {user.name}!</h2>
            <p>Given name: {user.givenName}</p>
            <p>Family name: {user.familyName}</p>
            <p>SSO Id: {user.id}</p>

            <form  action={async () => {
                "use server"
                return await signOut({redirectTo: "/"});


           }}>
                <button type="submit">Sign out</button>
            </form>
        </div>
    )
}