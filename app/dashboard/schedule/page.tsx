// import Calendar from 'react-calendar'; //TODO
// import 'react-calendar/dist/Calendar.css';
//
// type DueDateType = {
//     date: string; // Use string to handle date serialization
//     homework: string;
// };
//
// import Calendar from "@/icons/Calendar";
// import {prisma} from "@/prisma";
import {getCurrentStudent} from "@/app/actions/getCurrentStudent";

export default async function SchedulePage() {

    const student = await getCurrentStudent();

    if (student == null)
    {
        return;
    }

/*TODO
    PLAN:
    WE TAKE ALL SUBJECTS WHERE THE STUDENT IS ENROLLED AND FOR EACH ONE WE TAKE ALL ASSIGNMENTS
    WE THEN CHECK IF THERE ARE ANY DUE DATES FOR THE ASSIGNMENTS
    WE DISPLAY THEM ON THE CALENDAR
 */
    // const subjects = await prisma.subject.findMany({
    //     where: {
    //         students: {
    //             some: {
    //                 id: student.id
    //             }
    //         }
    //     }
    // });
    // }


    return (
        <div className="flex flex-col items-center">
            {/*<h2 className="text-2xl font-bold mb-4">Schedule</h2>*/}
            {/*<Calendar*/}
            {/*    //TODO THIS*/}
            {/*/>*/}
        </div>
    );
}
    // const response = await fetch('https://example.com/api/due-dates', {
    //     mode: 'cors',
    //     method: 'GET',
    //     headers: {
    //         'Content-Type': 'application/json',
    //         'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
    //     },
    // });
    // const data: DueDateType[] = await response.json();
    //
    // const dueDates = data.map(dueDate => ({
    //     date: new Date(dueDate.date),
    //     homework: dueDate.homework
    // }));


// }


//
// /*
// import React, { useState, useEffect } from 'react';
// import Calendar from 'react-calendar';
// import 'react-calendar/dist/Calendar.css';
//
// const SchedulePage: React.FC = () => {
//   const [dueDates, setDueDates] = useState([
//     { date: new Date(2024, 4, 15), homework: 'Math: Chapter 5' },
//     { date: new Date(2024, 4, 20), homework: 'English: Essay' },
//     // Add more due dates as needed
//   ]);
//
//   useEffect(() => {
//     // Fetch the due dates from the server and update the state
//     // setDueDates(fetchedDueDates);
//   }, []);
//
//   return (
//     <div className="flex flex-col items-center">
//       <h2 className="text-2xl font-bold mb-4">Schedule</h2>
//       <Calendar
//         tileContent={({ date, view }) => {
//           if (view === 'month') {
//             const dueDate = dueDates.find(dueDate =>
//               dueDate.date.getDate() === date.getDate() &&
//               dueDate.date.getMonth() === date.getMonth() &&
//               dueDate.date.getFullYear() === date.getFullYear()
//             );
//             return (
//               <div>
//                 <p>Day {date.getDate()}</p>
//                 {dueDate && <p className="text-red-500">{dueDate.homework}</p>}
//               </div>
//             );
//           }
//         }}
//       />
//     </div>
//   );
// };
//
// export default SchedulePage;
//  */