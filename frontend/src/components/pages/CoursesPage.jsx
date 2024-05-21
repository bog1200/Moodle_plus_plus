import React, {useEffect, useState} from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import Course from '../Course.jsx';
import './CoursesPage.css'
import CourseAsList from "../CourseAsList.jsx";


function CoursesPage() {
    const [selectedOption, setSelectedOption] = useState(0);
    //const [courses, setCourses] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [searchTerm, setSearchTerm] = useState('');
    const coursesPerPage = 9;


    const [loading, setLoading] = useState(true);
    useEffect(() => {
        const fetchCourses = async () => {
            let initialCourses = [];
            // ... fetch request
            setCourses(initialCourses);
            setLoading(false); // Set loading to false after the data has been fetched
        };

        fetchCourses().catch(error => console.error('Failed to fetch courses:', error));
    }, []);



    const [courses, setCourses] = useState([]);

    useEffect(() => {
        const fetchCourses = async () => {
            //let initialCourses = [];
            const response = await fetch('https://mpp.romail.app/api/v1/subject/student/1', {
                mode: 'cors',
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': "Bearer " + localStorage.getItem('accessToken')
                },
            });
            const data = await response.json();
            console.log(data);
            let initialCourses = data.map(item => ({
                name: item.name,
                professor: item.description
            }));
            // for() // for each data element i retrieve and make name and description
            // if (data.name && data.description) {
            //     initialCourses.push({name: `${data.name}`, professor: `${data.description}`});
            // }
            console.log("initial courses array: ");console.log(initialCourses);
            setCourses(initialCourses);
            setLoading(false);
        };

        fetchCourses().catch(error => console.error('Failed to fetch courses:', error));
    }, []);
    console.log("courses array afterwards: ");console.log(courses);

    // const [courses, setCourses] = useState(() => {
    //     let initialCourses = [];
    //     for(let i = 1; i <= 50; i++) {
    //         initialCourses.push({ name: `Course ${i}`, professor: `Professor ${i}` });
    //     }
    //     return initialCourses;
    // });

    // Initialize displayedCourses with the initial list of courses
    const [displayedCourses, setDisplayedCourses] = useState(courses);
    useEffect(() => {
        setDisplayedCourses(courses);
    }, [courses]);

    // useEffect(() => {
    //     // Replace with your actual API endpoint
    //     // fetch('https://api.example.com/courses?username=' + encodeURIComponent('XCriwn'))
    //     fetch('https://mpp.romail.app/api/v1/course/getBySubject/')
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

    if (loading) {
        return <div>Loading...</div>; // Display a loading message or a spinner
    }

    const startIndex = (currentPage - 1) * coursesPerPage;
    const selectedCourses = displayedCourses.slice(startIndex, startIndex + coursesPerPage);
    console.log("Courses.name variable is: ");console.log(courses.name);
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
                        selectedCourses.map((courses, index) => (

                            <Course key={index} name={courses.name} professor={courses.professor}/>
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