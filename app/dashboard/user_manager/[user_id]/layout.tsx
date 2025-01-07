import {getUserRoles} from "@/app/actions/user";
import type {Metadata} from "next";

export const metadata: Metadata = {
    title: "User Manager | Moodle++",
    description: "User Manager for Moodle++",
};

export default async function UserManagerEditLayout({children}: {children: React.ReactNode}) {
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