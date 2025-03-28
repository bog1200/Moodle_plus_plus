import Link from 'next/link';
import AcademicCap from "@/icons/AcademicCap";
import BookOpen from "@/icons/BookOpen";
import Calendar from "@/icons/Calendar";
import Person from "@/icons/Person";
import Logout from "@/icons/Logout";
import User from "@/icons/User";
import {signOut} from "@/auth";
import SvgIcon from "@/app/components/SvgIcon";
import {getUserRoles} from "@/app/actions/user";
import Image from "next/image";

// import logo from '../public/static/photos/logo.jpg';


export default async function AsideDesktop() {
    const userRoles = await getUserRoles();
    if(!userRoles){
        return null;
    }

    return (
        <aside className="flex flex-row lg:flex-col h-full lg:h-screen justify-around items-center border shadow-md bg-background ">
            <div>
                <Link href="/dashboard">
                    <div className={"scale-75 lg:scale-100"}>
                        <Image src={"/logo.svg"} alt={"logo"} width={100} height={100} priority={true}></Image>
                    </div>

                </Link>
            </div>
            <div className="flex flex-row lg:flex-col items-center z-10">
                <ul className={"flex lg:block"}>
                    <li className="">
                        <Link href="/dashboard" className="lg:mr-10">
                            <SvgIcon heading={"Dashboard"} svg={<AcademicCap color={"rgb(134, 8, 140)"}/>}/>
                        </Link>
                    </li>
                    <li>
                        <Link href="/dashboard/courses" className="lg:mr-10">
                            {/*<Image src={calendarCheck} alt="calendar check" width={30} height={30}/>*/}
                            <SvgIcon heading={"Courses"} svg={<BookOpen color={"rgb(22, 87, 171)"}/>}/>
                        </Link>
                    </li>
                    <li>
                        <Link href="/dashboard/schedule" className="lg:mr-10">
                            {/*<Image src={calendarEventFill} alt="calendar event" width={30} height={30}/>*/}
                           <SvgIcon  heading={"Schedule"} svg={ <Calendar color={"#e8ca20"}/>} />
                        </Link>
                    </li>
                    <li>
                    <Link href="/dashboard/profile" className="lg:mr-10">
                        {/*<Image src={personFill} alt="profile" width={30} height={30}/>*/}
                      <SvgIcon heading={"Profile"} svg={ <User  color={"#3dc461"}/> }/>
                    </Link>
                    </li>
                    {userRoles.isAdmin && <li>
                        <Link href="/dashboard/file_manager" className="lg:mr-10">
                            {/*<Image src={calendarCheck} alt="calendar check" width={30} height={30}/>*/}
                            <SvgIcon heading={"File Manager"} svg={<BookOpen color={"#faa"}/>}/>
                        </Link>
                    </li> }

                    {userRoles.isAdmin && <li>
                        <Link href="/dashboard/user_manager" className="lg:mr-10">
                            {/*<Image src={calendarCheck} alt="calendar check" width={30} height={30}/>*/}
                            <SvgIcon heading={"User Manager"} svg={<Person color={"#ffa"}/>}/>
                        </Link>
                    </li> }



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
