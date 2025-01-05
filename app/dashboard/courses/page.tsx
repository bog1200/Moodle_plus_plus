"use client"

import React from 'react';
import { Course } from '../../components/Course';
import { CourseAsList } from '../../components/CourseAsList';
import SearchIcon from '@/icons/SearchIcon';

type CourseType = {
  name: string;
  professor: string;
  subject: string;
};

const CoursesPage: React.FC = () => {
  const courses: CourseType[] = [
    // Example data, replace with actual data fetching logic
    { name: 'Course 1', professor: 'Prof. A', subject: 'Subject 1' },
    { name: 'Course 2', professor: 'Prof. B', subject: 'Subject 2' },
  ];
  const [selectedOption, setSelectedOption] = React.useState(0);
  const [currentPage, setCurrentPage] = React.useState(1);
  const [searchTerm, setSearchTerm] = React.useState('');
  const [displayedCourses, setDisplayedCourses] = React.useState<CourseType[]>(courses);
  const coursesPerPage = 9;

  const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedOption(parseInt(event.target.value));
  };

  const handleNextPage = () => {
    setCurrentPage(currentPage + 1);
  };

  const handlePreviousPage = () => {
    setCurrentPage(currentPage - 1);
  };

  // React.useEffect(() => {
  //   const filteredCourses = courses.filter(course =>
  //       course.name.toLowerCase().includes(searchTerm.toLowerCase())
  //   );
  //   setDisplayedCourses(filteredCourses);
  // }, [courses, searchTerm]);

  const handleSearch = () => {
    if (searchTerm.trim() === '') {
      setDisplayedCourses(courses);
    } else {
      const filteredCourses: CourseType[] = courses.filter(course => course.name.toLowerCase().includes(searchTerm.toLowerCase()));
      setDisplayedCourses(filteredCourses);
    }
    setCurrentPage(1);
  };

  const startIndex = (currentPage - 1) * coursesPerPage;
  const selectedCourses = displayedCourses.slice(startIndex, startIndex + coursesPerPage);

  return (
      <div className="flex flex-col items-center">
        <h2 className="text-4xl">Courses</h2>
        <div className="flex items-center mb-3 p-4 rounded-lg shadow-md bg-background text-foreground border border-foreground">
          <p className="m-3 w-25 rounded-3">Display as: </p>
          <select
              className="form-select me-2 px-4 py-2 border bg-background text-foreground rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={selectedOption} onChange={handleSelectChange}>
            <option value="0">Card</option>
            <option value="1">List</option>
          </select>
          <input
              type="search"
              className="form-control me-2 px-4 py-2 bg-background text-foreground  border rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
              placeholder="Search..."
              value={searchTerm}
              onChange={e => setSearchTerm(e.target.value)}
          />
          <button onClick={handleSearch} className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700">
            <SearchIcon/>
          </button>
        </div>

        <div className="flex flex-wrap justify-center mt-5 w-128">
          {selectedOption === 0 ? (
              selectedCourses.map((course, index) => (
                  <Course key={index} name={course.name} professor={course.professor} subject={course.subject} />
              ))
          ) : (
              <ul>
                {selectedCourses.map((course, index) => (
                    <li key={index}>
                      <CourseAsList name={course.name} professor={course.professor} subject={course.subject} />
                    </li>
                ))}
              </ul>
          )}
        </div>
        <div className="flex justify-between mt-5">
          <button onClick={handlePreviousPage} disabled={currentPage === 1}
                  className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700 disabled:bg-gray-400">
            Previous
          </button>
          <button onClick={handleNextPage} disabled={startIndex + coursesPerPage >= courses.length}
                  className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700 disabled:bg-gray-400 ml-2">
            Next
          </button>
        </div>
      </div>
  );
};

export default CoursesPage;


// import React, { useEffect, useState } from 'react';
// import searchIcon from 'bootstrap-icons/icons/search.svg';
// import {Course} from '../components/Course';
// import {CourseAsList} from '../components/CourseAsList';
//
// const CoursesPage: React.FC = () => {
//   const [selectedOption, setSelectedOption] = useState(0);
//   const [currentPage, setCurrentPage] = useState(1);
//   const [searchTerm, setSearchTerm] = useState('');
//   const [loading, setLoading] = useState(true);
//   const [courses, setCourses] = useState([]);
//   const [displayedCourses, setDisplayedCourses] = useState([]);
//   const coursesPerPage = 9;
//
//   useEffect(() => {
//     const fetchCourses = async () => {
//       const response = await fetch('https://mpp.romail.app/api/v1/subject/student/1', {
//         mode: 'cors',
//         method: 'GET',
//         headers: {
//           'Content-Type': 'application/json',
//           'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
//         },
//       });
//       const data = await response.json();
//       const initialCourses = data.map(item => ({
//         name: item.name,
//         professor: item.description,
//         subject: item.id
//       }));
//       setCourses(initialCourses);
//       setLoading(false);
//     };
//
//     fetchCourses().catch(error => console.error('Failed to fetch courses:', error));
//   }, []);
//
//   useEffect(() => {
//     setDisplayedCourses(courses);
//   }, [courses]);
//
//   const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
//     setSelectedOption(parseInt(event.target.value));
//   };
//
//   const handleNextPage = () => {
//     setCurrentPage(currentPage + 1);
//   };
//
//   const handlePreviousPage = () => {
//     setCurrentPage(currentPage - 1);
//   };
//
//   const handleSearch = () => {
//     const filteredCourses = courses.filter(course => course.name.toLowerCase().includes(searchTerm.toLowerCase()));
//     setCurrentPage(1);
//     setDisplayedCourses(filteredCourses);
//   };
//
//   if (loading) {
//     return <div>Loading...</div>;
//   }
//
//   const startIndex = (currentPage - 1) * coursesPerPage;
//   const selectedCourses = displayedCourses.slice(startIndex, startIndex + coursesPerPage);
//
//   return (
//     <div className="flex flex-col items-center">
//       <h2>Courses</h2>
//       <div className="flex items-center mb-3">
//         <p className="m-3 w-25 rounded-3">Display as: </p>
//         <select className="form-select me-2" value={selectedOption} onChange={handleSelectChange}>
//           <option value="0">Card</option>
//           <option value="1">List</option>
//         </select>
//         <input
//           type="search"
//           className="form-control me-2"
//           placeholder="Search..."
//           value={searchTerm}
//           onChange={e => setSearchTerm(e.target.value)}
//         />
//         <button onClick={handleSearch}>
//           <img src={searchIcon} alt="Search" width="30" height="30" />
//         </button>
//       </div>
//
//       <div className="flex flex-wrap justify-center mt-5 w-128">
//         {selectedOption === 0 ? (
//           selectedCourses.map((course, index) => (
//             <Course key={index} name={course.name} professor={course.professor} subject={course.subject} />
//           ))
//         ) : (
//           <ul>
//             {selectedCourses.map((course, index) => (
//               <li key={index}>
//                 <CourseAsList name={course.name} professor={course.professor} subject={course.subject} />
//               </li>
//             ))}
//           </ul>
//         )}
//       </div>
//       <div className="flex justify-between mt-5">
//         <button onClick={handlePreviousPage} disabled={currentPage === 1}>Previous</button>
//         <button onClick={handleNextPage} disabled={startIndex + coursesPerPage >= courses.length}>Next</button>
//       </div>
//     </div>
//   );
// };
//
// export default CoursesPage;