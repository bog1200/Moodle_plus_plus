// import Image from 'next/image';
import Link from 'next/link';
import AcademicCap from "@/icons/AcademicCap";
import BookOpen from "@/icons/BookOpen";
import Calendar from "@/icons/Calendar";
import Logout from "@/icons/Logout";
import User from "@/icons/User";
import {signOut} from "@/auth";

// import logo from '../public/static/photos/logo.jpg';


export default async function Aside() {


    return (
        <aside className="flex justify-center items-center py-3 border bg-gray-500 shadow-sm rounded-lg">
            <div className="flex">
                {/*<Image src={logo} alt="Logo" width={50} height={50} className="rounded-full"/>*/}
                <Link href="/dashboard" className="mr-10">
                    <AcademicCap heading={"Logo"}/>
                </Link>
            </div>

            <Link href="/dashboard" className="mr-10">
                <AcademicCap heading={"Dashboard"} color={"rgb(134, 8, 140)"}/>
            </Link>
            {/*TODO: Add the rest of the links and make them linear*/}
            <Link href="/courses" className="mr-10">
                {/*<Image src={calendarCheck} alt="calendar check" width={30} height={30}/>*/}
                <BookOpen heading={"Courses"} color={"rgb(22, 87, 171)"}/>
            </Link>
            <Link href="/schedule" className="mr-10">
                {/*<Image src={calendarEventFill} alt="calendar event" width={30} height={30}/>*/}
                <Calendar heading={"Schedule"} color={"#e8ca20"}/>
            </Link>
            <Link href="/profile" className="mr-10">
                {/*<Image src={personFill} alt="profile" width={30} height={30}/>*/}
                <User heading={"Profile"} color={"#3dc461"}/>
            </Link>

            <form action={async () => {
                "use server"
                return await signOut({redirectTo: "/"});


            }}>
                <button type="submit" className="mr-10"><Logout heading={"Log out"}/></button>
            </form>
        </aside>
    );
};
