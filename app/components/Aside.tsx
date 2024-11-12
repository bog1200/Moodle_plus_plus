import Image from 'next/image';
import Link from 'next/link';
import AcademicCap from "@/icons/AcademicCap";
import BookOpen from "@/icons/BookOpen";
import Calendar from "@/icons/Calendar";
import Logout from "@/icons/Logout";
import User from "@/icons/User";
import {signOut} from "@/auth";
import SvgIcon from "@/app/components/SvgIcon";

// import logo from '../public/static/photos/logo.jpg';


export default async function Aside() {


    return (
        <aside className="flex flex-col h-screen overflow-clip justify-around items-center border bg-gray-100 shadow-md">
            <div className="flex">
                <Image src="/favicon.ico" alt="Logo" width={150} height={150} className="rounded-full"/>

            </div>
            <div className="flex flex-col items-center  overflow-hidden">
                <ul>
                    <li className="">
                        <Link href="/dashboard"  className="mr-10">
                        <SvgIcon heading={"Dashboard"} svg={ <AcademicCap color={"rgb(134, 8, 140)"}/>}/>
                    </Link>
                    </li>
                    <li>
                        <Link href="/dashboard/courses" className="mr-10">
                            {/*<Image src={calendarCheck} alt="calendar check" width={30} height={30}/>*/}
                           <SvgIcon heading={"Courses"} svg={<BookOpen color={"rgb(22, 87, 171)"} /> }/>
                        </Link>
                    </li>
                    <li>
                        <Link href="/dashboard/schedule" className="mr-10">
                            {/*<Image src={calendarEventFill} alt="calendar event" width={30} height={30}/>*/}
                           <SvgIcon  heading={"Schedule"} svg={ <Calendar color={"#e8ca20"}/>} />
                        </Link>
                    </li>
                    <li>
                    <Link href="/dashboard/profile" className="mr-10">
                        {/*<Image src={personFill} alt="profile" width={30} height={30}/>*/}
                      <SvgIcon heading={"Profile"} svg={ <User  color={"#3dc461"}/> }/>
                    </Link>
                    </li>
                    <li>
                        <li>
                            <Link href="/dashboard/file_manager" className="mr-10">
                                {/*<Image src={calendarCheck} alt="calendar check" width={30} height={30}/>*/}
                                <SvgIcon heading={"File Manager (Admin)"} svg={<BookOpen color={"#faa"}/>}/>
                            </Link>
                        </li>
                    </li>
                </ul>

                {/*TODO: Add the rest of the links and make them linear*/}


            </div>


            <form className={""} action={async () => {
                "use server"
                return await signOut({redirectTo: "/"});


            }}>
                <button type="submit" className={"w-full"}>
                <SvgIcon heading={"Log out"} svg={<Logout color={"#f00"}/>}/>
                    </button>
            </form>
        </aside>
    );
};
