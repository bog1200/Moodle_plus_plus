import React, {useEffect, useState} from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";
import Course from '../Course.jsx';
import RecentCourses from "../RecentCourses.jsx";
import './CoursesPage.css'

function CoursesPage() {
    const [selectedOption, setSelectedOption] = useState(0);
    //const [courses, setCourses] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [searchTerm, setSearchTerm] = useState('');
    const coursesPerPage = 9;

    // Create an array of 25 courses
    // HARD CODED UNTIL API IS READY
    let courses = [];
    for(let i = 1; i <= 25; i++) {
        courses.push({ name: `Course ${i}`, professor: `Professor ${i}` });
    }

    // useEffect(() => {
    //     // Replace with your actual API endpoint
    //     fetch('https://api.example.com/courses?username=' + encodeURIComponent('XCriwn'))
    //         .then(response => response.json())
    //         .then(data => setCourses(data));
    // }, []);

    const handleSelectChange = (event) => {
        setSelectedOption(event.target.value);
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
        courses = filteredCourses;
    };

    const startIndex = (currentPage - 1) * coursesPerPage;
    const selectedCourses = courses.slice(startIndex, startIndex + coursesPerPage);

    return (
        <div className="row">
            <div className="col-10">
                <h2>Courses</h2>
                <div className="d-flex align-items-center mb-3">
                    <select className="form-select me-2" onChange={handleSelectChange}>
                        <option value="0">Choose...</option>
                        <option value="3">Option 1</option>
                        <option value="5">Option 2</option>
                        <option value="8">Option 3</option>
                    </select>
                    <input type="search" className="form-control me-2" placeholder="Search..." value={searchTerm}
                           onChange={e => setSearchTerm(e.target.value)}/>
                    <button onClick={handleSearch}><img src={searchIcon} alt="Search" width="30" height="30"/></button>
                </div>

                <div className="courses_div d-flex align-items-center mb-3">
                    {selectedCourses.map((course, index) => (
                        <Course key={index} name={course.name} professor={course.professor} />
                    ))}
                </div>
                <div className="courses_div d-flex align-items-center mb-3">
                    <button onClick={handlePreviousPage} disabled={currentPage === 1}>Previous</button>
                    <button onClick={handleNextPage} disabled={startIndex + coursesPerPage >= courses.length}>Next</button>
                </div>


            </div>
            <div className="col-2">

            </div>
        </div>
    );
}

export default CoursesPage;