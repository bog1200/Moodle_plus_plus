import React, { useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import DueSubmissionAlert from "../DueSubmissionAlert.jsx";
import RecentCourses from "../RecentCourses.jsx";
import * as subjects from "react-bootstrap/ElementChildren";
import {useNavigate} from "react-router-dom";

function MainPage() {
    const [id, setCourseId] = useState('');
    const [subject_id, setSubjectId] = useState('');
    const [courses, setCourses] = useState([]);
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
    event.preventDefault();


        await fetch('https://mpp.romail.app/api/v1/course/getBySubject', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                course_id: id,
                subject_id: subject_id,
            }),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.accessToken) {
                    localStorage.setItem('accessToken', data.accessToken);
                    navigate('/');
                } else {
                    alert('Incorrect username or password');
                }
            });
    }


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

                {subjects.map((subject, index) => (
                    //<DueSubmissionAlert key={index} subject={subject} text={texts[index]} />
                    <DueSubmissionAlert key={index} subject={subject} text={subjects[index]}
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