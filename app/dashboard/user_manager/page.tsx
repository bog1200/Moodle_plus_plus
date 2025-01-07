import {getAllUsers} from "@/app/actions/user";
import Link from "next/link";


export default async function UserManager() {
    const users = await getAllUsers();

    return (
        <div>
            <h1 className={"text-3xl m-2"}>User Manager:</h1>
            <ul>
                {users.map((user) => (
                    <li className={"border-2 border-foreground rounded-xl m-2 p-2"} key={user.id}>
                        <Link href={`/dashboard/user_manager/${user.id}`}>
                        <p>ID: {user.id}</p>
                        <p>Name: {user.name}</p>
                        <p>Email: {user.email}</p>
                        <p>Roles: <span>{user.isAdmin && "Admin"}</span>
                            <span>{user.isTeacher && "Teacher"}</span>
                            <span>{user.isStudent && "Student"}</span>
                            <span>{!user.isAdmin && !user.isTeacher && !user.isStudent && "None"}</span>
                        </p>
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}