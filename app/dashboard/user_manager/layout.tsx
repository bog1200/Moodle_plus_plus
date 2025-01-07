import {getUserRoles} from "@/app/actions/user";

export default async function UserManagerLayout({children}: {children: React.ReactNode}) {
    const roles = await getUserRoles();
    if (!roles!.isAdmin) {
        return (
            <h1>Unauthorized</h1>
        );
    }
    return (
        <div>
            {children}
        </div>
    );
}