"use client";
import {getUserById, updateUser} from "@/app/actions/user";
import {useState, useEffect} from "react";
import {useRouter} from "next/navigation";
import {User} from "@prisma/client";


export default function UserEditor({ params }: { params: Promise<{ user_id: string }> }) {
    const router = useRouter();
    const [profile, setProfile] = useState<User|null|undefined>(undefined);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [isAdmin, setIsAdmin] = useState<boolean>(false);
    const [isTeacher, setIsTeacher] = useState<boolean>(false);
    const [isStudent, setIsStudent] = useState<boolean>(false);
    const [phone, setPhone] = useState("");

    useEffect(() => {
        const fetchData = async () => {
            const { user_id } = await params;
            const profile = await getUserById(user_id);
            setProfile(profile||null);
            setName(profile?.name || "");
            setEmail(profile?.email || "");
            setIsAdmin(profile?.isAdmin || false);
            setIsTeacher(profile?.isTeacher || false);
            setIsStudent(profile?.isStudent || false);
            setPhone(profile?.phone || "");
        };
        fetchData();
    }, [params]);

    if (profile === null) {
        return <h1>User not found</h1>;
    }
    if (profile === undefined) {
        return <h1>Loading...</h1>;
    }





    const handleSave = async () => {
        const updatedProfile: User = {
            ...profile,
            name: name,
            isAdmin: isAdmin,
            isTeacher: isTeacher,
            isStudent: isStudent,
            phone: phone
        };

        // Save the updated profile
        updateUser(updatedProfile).then(() => {
            router.back();
        }
        );
    };

    return (
        <div className={"w-full"}>
            <h1 className={"text-3xl"}>User Editor</h1>
            <p>ID: {profile.id}</p>
            <div className={"flex flex-col  items-center border-2 border-foreground rounded-xl p-4 m-4 h-fit"}>
                <p className={"m-2"}>Name</p>
                <input
                    className={"text-foreground bg-background border-2 border-foreground  w-full p-4 m-4 rounded-full"}
                    type={"text"} value={name} onChange={(e) => setName(e.target.value)}/>
                <p className={"m-2"}>Email</p>
                <span
                    className={"text-gray-500 bg-background border-2 border-foreground  w-full p-4 m-4 rounded-full"}> {email} </span>
                <p className={"m-2"}>Phone</p>
                <input
                    className={"text-foreground bg-background border-2 border-foreground  w-full p-4 m-4 rounded-full"}
                    type={"text"} value={phone} onChange={(e) => setPhone(e.target.value)}/>
                <p className={"m-2"}>Roles</p>
                <div className={"w-full flex flex-row content-around rounded-full"}>
                    <label
                        className={`p-4 border-foreground rounded-full border-2 w-1/3 text-center m-4 text-white ${isAdmin ? "bg-green-500" : "bg-red-500"}`}
                        onClick={() => setIsAdmin(!isAdmin)}>
                        Admin
                    </label>
                    <label
                        className={`p-4 border-foreground rounded-full border-2  w-1/3 text-center m-4 text-white ${isTeacher ? "bg-green-500" : "bg-red-500"}`}
                        onClick={() => setIsTeacher(!isTeacher)}>
                        Teacher
                    </label>
                    <label
                        className={`p-4 border-foreground rounded-full border-2  w-1/3 text-center m-4 text-white ${isStudent ? "bg-green-500" : "bg-red-500"}`}
                        onClick={() => setIsStudent(!isStudent)}>
                        Student
                    </label>

                </div>
                <div className={"flex w-full items-center"}>

                    <button className={"border-2 rounded-full border-foreground p-4 my-4 mx-32 w-full"}
                            onClick={handleSave}>Save
                    </button>
                </div>
            </div>
            <button onClick={() => router.back()}> {"<"} Go back to User Manager</button>
        </div>
    );
}