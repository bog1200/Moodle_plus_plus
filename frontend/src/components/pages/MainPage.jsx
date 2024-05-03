import React from 'react';
import { useMediaQuery } from 'react-responsive';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";
import DueSubmissionAlert from "../DueSubmissionAlert.jsx";
import RecentCourses from "../RecentCourses.jsx";

function MainPage() {

    const courses = [
        { name: 'Course 1', professor: 'Professor 1' },
        { name: 'Course 2', professor: 'Professor 2' },
        { name: 'Course 3', professor: 'Professor 3' },
    ];
    let subjects = ["Subject 1", "Subject 2", "Subject 3"];
    let texts = ["Text 1", "Text 2", "Text 3"];

    const isDesktopOrLaptop = useMediaQuery({
        query: '(min-device-width: 1224px)'
    });
    const isTabletOrMobile = useMediaQuery({
        query: '(max-device-width: 1224px)'
    });

    return (
        <div className="row">
            <div className={isDesktopOrLaptop ? "desktop-class" : "mobile-class"}>
                <h2>Timeline</h2>

                <DueSubmissionAlert subject={subjects[0]} text={texts[0]} />
                <DueSubmissionAlert subject={subjects[1]} text={texts[1]} />
                <DueSubmissionAlert subject={subjects[2]} text={texts[2]} />

                <RecentCourses courses={courses} />

            </div>

        </div>
    );
}

export default MainPage;