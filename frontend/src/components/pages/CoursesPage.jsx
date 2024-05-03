import React, {useEffect, useState} from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";
import Course from '../Course.jsx';
import RecentCourses from "../RecentCourses.jsx";
import './CoursesPage.css'
import CourseAsList from "../CourseAsList.jsx";
import courseAsList from "../CourseAsList.jsx";


function CoursesPage() {
    const [selectedOption, setSelectedOption] = useState(0);
    //const [courses, setCourses] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [searchTerm, setSearchTerm] = useState('');
    const coursesPerPage = 9;

    // Create an array of 25 courses
    // HARD CODED UNTIL API IS READY
    const [courses, setCourses] = useState(() => {
        let initialCourses = [];
        for(let i = 1; i <= 50; i++) {
            initialCourses.push({ name: `Course ${i}`, professor: `Professor ${i}` });
        }
        return initialCourses;
    });

    // Initialize displayedCourses with the initial list of courses
    const [displayedCourses, setDisplayedCourses] = useState(courses);

    // useEffect(() => {
    //     // Replace with your actual API endpoint
    //     fetch('https://api.example.com/courses?username=' + encodeURIComponent('XCriwn'))
    //         .then(response => response.json())
    //         .then(data => setCourses(data));
    // }, []);

    const handleSelectChange = (event) => {
        setSelectedOption(parseInt(event.target.value));
    };

    const handleNextPage = () => {
        setCurrentPage(currentPage + 1);
    };

    const handlePreviousPage = () => {
        setCurrentPage(currentPage - 1);
    };



    const handleSearch = () => {
        const filteredCourses = courses.filter(course => course.name.toLowerCase().includes(searchTerm.toLowerCase()));
        setCurrentPage(1);
        setDisplayedCourses(filteredCourses); // Update courses state variable
    };

    const startIndex = (currentPage - 1) * coursesPerPage;
    const selectedCourses = displayedCourses.slice(startIndex, startIndex + coursesPerPage);

    return (
        <div className="row">
            <div className="col-10">
                <h2>Courses</h2>
                <div className="d-flex align-items-center mb-3">
                    <p className="m-3 w-25 rounded-3">Display as: </p>
                    <select className="form-select me-2" value={selectedOption} onChange={handleSelectChange}>
                        <option value="0">Card</option>
                        <option value="1">List</option>
                    </select>
                    <input type="search" className="form-control me-2" placeholder="Search..." value={searchTerm}
                           onChange={e => setSearchTerm(e.target.value)}/>
                    <button onClick={handleSearch}><img src={searchIcon} alt="Search" width="30" height="30"/></button>
                </div>

                <div className="courses_div d-flex align-items-center mb-3">
                    {selectedOption === 0 ? (
                        selectedCourses.map((course, index) => (
                            <Course key={index} name={course.name} professor={course.professor}/>
                        ))
                    ) : (

                        <ul>
                            {selectedCourses.map((course, index) => (
                                <li key={index}>
                                    <CourseAsList name={course.name} professor={course.professor}/>
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
                <div className="courses_div d-flex align-items-center mb-3">
                    <button onClick={handlePreviousPage} disabled={currentPage === 1}>Previous</button>
                    <button onClick={handleNextPage} disabled={startIndex + coursesPerPage >= courses.length}>Next
                    </button>
                </div>


            </div>
            <div className="col-2">

            </div>
        </div>
    );
}

export default CoursesPage;