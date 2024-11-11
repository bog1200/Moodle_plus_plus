import { getCurrentStudent } from "@/app/actions/getCurrentStudent";
import { getSubjectEnrollment } from "@/app/actions/getSubjectEnrollment";
// import Calendar from "@/icons/Calendar";
import {prisma} from "@/prisma";

export default async function SchedulePage() {
    const student = await getCurrentStudent();
    const subjectEnrollment = student ? await getSubjectEnrollment() : null;

    if(subjectEnrollment == null){
        return <p className="text-center text-gray-500">No data available</p>;
    }

    const subjectList = subjectEnrollment.map(sub => sub.subjectId);
    const subject = await prisma.subject.findMany({
        where: {
            id: {
                in: subjectList
            }
        }
    });
//TODO ALSO DO A ASSIGNMENT FINDMANY

    if (!student || !subject) {
        return <p className="text-center text-gray-500">No data available</p>;
    }

    console.log(subject);

    return (
        <div className="flex flex-col items-center">
            <h2 className="text-2xl font-bold mb-4">Schedule</h2>
            <p>Student ID: {student.id}</p>
            <p>Subject IDs: {subject.map(sub => sub.id).join(', ')}</p>
            {/*<ul>*/}
            {/*    {subject.map(sub => (*/}
            {/*        <li key={sub.id}>*/}
            {/*            <p>Subject: {sub.name}</p>*/}
            {/*            <ul>*/}
            {/*                {sub.assignments.map(assignment => (*/}
            {/*                    <li key={assignment.id}>*/}
            {/*                        {assignment.title}: {new Date(assignment.dueDate).toLocaleDateString()}*/}
            {/*                    </li>*/}
            {/*                ))}*/}
            {/*            </ul>*/}
            {/*        </li>*/}
            {/*    ))}*/}
            {/*</ul>*/}
        </div>
    );
}


// import {prisma} from "@/prisma";
// import {getCurrentStudent} from "@/app/actions/getCurrentStudent";
// import {getSubjectEnrollment} from "@/app/actions/getSubjectEnrollment";
//
// export default async function SchedulePage() {
//
//     const student = await getCurrentStudent();
//
//     if (student == null)
//     {
//         return;
//     }
//
//
//     const subjectEnrollment = await getSubjectEnrollment()
//
//     if (subjectEnrollment == null)
//     {
//         return;
//     }
//
//     const subject = await prisma.subjectEnrollment.findMany({
 //         where: {
 //             studentId: student.id
 //         }
 //     })
 //
 //     console.log(student);
 //     console.log(subject);
 //
 //     return (
 //         <div className="flex flex-col items-center">
 //             <h2 className="text-2xl font-bold mb-4">Schedule</h2>
 //             <p>{student.id}</p>
 //             <p>{subject.id}</p>
 //
 //         </div>
 //     );
 // }

 /*TODO
     PLAN:
     WE TAKE THE STUDENTS FROM SUBJECT ENROLLMENT TABLE
     WE TAKE ALL SUBJECTS WHERE THE STUDENT IS ENROLLED AND FOR EACH ONE WE TAKE ALL ASSIGNMENTS
     WE THEN CHECK IF THERE ARE ANY DUE DATES FOR THE ASSIGNMENTS
     WE DISPLAY THEM ON THE CALENDAR
  */

 //     // const response = await fetch('https://example.com/api/due-dates', {
 //     //     mode: 'cors',
 //     //     method: 'GET',
 //     //     headers: {
 //     //         'Content-Type': 'application/json',
 //     //         'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
 //     //     },
 //     // });
 //     // const data: DueDateType[] = await response.json();
 //     //
 //     // const dueDates = data.map(dueDate => ({
 //     //     date: new Date(dueDate.date),
 //     //     homework: dueDate.homework
 //     // }));
 //
 //
 // // }
 //
 //

 // // import Calendar from 'react-calendar'; //TODO
 // // import 'react-calendar/dist/Calendar.css';
 // //
 // // type DueDateType = {
 // //     date: string; // Use string to handle date serialization
 // //     homework: string;
 // // };
 // //
 // // import Calendar from "@/icons/Calendar";

 // //
 // //
 // // import React, { useState, useEffect } from 'react';
 // // import Calendar from 'react-calendar';
 // // import 'react-calendar/dist/Calendar.css';
 // //
 // // const SchedulePage: React.FC = () => {
 // //   const [dueDates, setDueDates] = useState([
 // //     { date: new Date(2024, 4, 15), homework: 'Math: Chapter 5' },
 // //     { date: new Date(2024, 4, 20), homework: 'English: Essay' },
 // //     // Add more due dates as needed
 // //   ]);
 // //
 // //   useEffect(() => {
 // //     // Fetch the due dates from the server and update the state
 // //     // setDueDates(fetchedDueDates);
 // //   }, []);
 // //
 // //   return (
 // //     <div className="flex flex-col items-center">
 // //       <h2 className="text-2xl font-bold mb-4">Schedule</h2>
 // //       <Calendar
 // //         tileContent={({ date, view }) => {
 // //           if (view === 'month') {
 // //             const dueDate = dueDates.find(dueDate =>
 // //               dueDate.date.getDate() === date.getDate() &&
 // //               dueDate.date.getMonth() === date.getMonth() &&
 // //               dueDate.date.getFullYear() === date.getFullYear()
 // //             );
 // //             return (
 // //               <div>
 // //                 <p>Day {date.getDate()}</p>
 // //                 {dueDate && <p className="text-red-500">{dueDate.homework}</p>}
 // //               </div>
 // //             );
 // //           }
 // //         }}
 // //       />
 // //     </div>
 // //   );
 // // };
 // //
 // // export default SchedulePage;
 // //
