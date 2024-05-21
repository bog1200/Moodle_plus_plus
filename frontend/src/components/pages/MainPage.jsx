import React, {useEffect, useState} from 'react';
import { useMediaQuery } from 'react-responsive';
import DueSubmissionAlert from "../DueSubmissionAlert.jsx";
import RecentCourses from "../RecentCourses.jsx";
import * as subjects from "react-bootstrap/ElementChildren";
import {useNavigate} from "react-router-dom";

function MainPage() {
    const [id, setCourseId] = useState('');
    const [subject_id, setSubjectId] = useState('');
    //const [courses, setCourses] = useState([]);
    const navigate = useNavigate();


    const [courses, setCourses] = useState([]);

    useEffect(() => {
        if (localStorage.getItem('accessToken') == null) {
            window.location.href = '/login';
        }
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

    const [displayedCourses, setDisplayedCourses] = useState(courses);
    useEffect(() => {
        setDisplayedCourses(courses);
    }, [courses]);

    const isDesktopOrLaptop = useMediaQuery({
        query: '(min-device-width: 1224px)'
    });
    const isTabletOrMobile = useMediaQuery({
        query: '(max-device-width: 1224px)'
    });

    return (
        <>
        <div className="row">
            <div className={isDesktopOrLaptop ? "desktop-class" : "mobile-class"}>
                <h2>Timeline</h2>

                {courses.map((courses, index) => (
                    //<DueSubmissionAlert key={index} subject={subject} text={texts[index]} />
                    <DueSubmissionAlert key={index} subject={courses.name} text={courses.professor + " is due."}
                    // onChange={event => setSubjectId(event.target.value)
                    //     event => setCourseId(event.target.value)}/>
                    />
                ))}

                <RecentCourses courses={courses} />

            </div>

        </div>
        </>
    );
}

export default MainPage;