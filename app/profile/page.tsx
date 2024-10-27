import {auth} from "@/auth";
import {PrismaClient} from "@prisma/client";
import {redirect} from "next/navigation"; //TODO

type ProfileType = {
  email: string;
  firstName: string;
  lastName: string;
  studentGroupId: string;
};

export default async function ProfilePage() {

    const session = await auth();

    if (session == null)
        return redirect("/")

    const user = session?.user;

    if (!user) {
        return <div>User not found</div>;
    }

    const prisma = new PrismaClient();

    const student = await prisma.student.findUnique({
        where: {
            id: parseInt(user.id)
        }
    })

    if (student == null)
    {
        return;
    }

    const person = await prisma.person.findUnique({
        where: {
            id: student.personId
        }
    })

    if (person == null) return



  return (
      <div className="flex flex-col items-center">
        <h2 className="text-2xl font-bold mb-4">Profile</h2>
        <div className="bg-gray-100 p-4 rounded-lg shadow-md w-full max-w-md">
          <h3 className="text-xl font-semibold mb-2">User details</h3>
          <p className="mb-2"><strong>Email: </strong>{profile.email}</p>
          <p className="mb-2"><strong>First name: </strong>{profile.firstName}</p>
          <p className="mb-2"><strong>Last name: </strong>{profile.lastName}</p>
          <p className="mb-2"><strong>Group: </strong>{profile.studentGroupId}</p>
        </div>
      </div>
  );
}


// import React, { useEffect, useState } from 'react';
// import { decodeToken } from 'react-jwt';
//
// const ProfilePage: React.FC = () => {
//   const [profile, setProfile] = useState({
//     email: '',
//     firstName: '',
//     lastName: '',
//     studentGroupId: ''
//   });
//   const token = decodeToken(localStorage.getItem('accessToken') || '');
//   const studentId = token?.sub || '';
//
//   useEffect(() => {
//     const fetchProfile = async () => {
//       try {
//         const response = await fetch(`https://mpp.romail.app/api/v1/student/${studentId}`, {
//           mode: 'cors',
//           method: 'GET',
//           headers: {
//             'Content-Type': 'application/json',
//             'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
//           },
//         });
//         const data = await response.json();
//         setProfile({
//           email: data.email,
//           firstName: data.firstName,
//           lastName: data.lastName,
//           studentGroupId: data.studentGroupId
//         });
//       } catch (error) {
//         console.error('Failed to fetch profile:', error);
//       }
//     };
//
//     fetchProfile();
//   }, [studentId]);
//
//   return (
//     <div className="flex flex-col items-center">
//       <h2 className="text-2xl font-bold mb-4">Profile</h2>
//       <div className="bg-gray-100 p-4 rounded-lg shadow-md w-full max-w-md">
//         <h3 className="text-xl font-semibold mb-2">User details</h3>
//         <p className="mb-2"><strong>Email: </strong>{profile.email}</p>
//         <p className="mb-2"><strong>First name: </strong>{profile.firstName}</p>
//         <p className="mb-2"><strong>Last name: </strong>{profile.lastName}</p>
//         <p className="mb-2"><strong>Group: </strong>{profile.studentGroupId}</p>
//       </div>
//     </div>
//   );
// };
//
// export default ProfilePage;