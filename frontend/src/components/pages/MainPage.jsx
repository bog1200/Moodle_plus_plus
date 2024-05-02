import React from 'react';
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


    return (
        <div className="row">
            <div className="col-8">
                <h2>Timeline</h2>

                <DueSubmissionAlert subject={subjects[0]} text={texts[0]} />
                <DueSubmissionAlert subject={subjects[1]} text={texts[1]} />
                <DueSubmissionAlert subject={subjects[2]} text={texts[2]} />

                <RecentCourses courses={courses} />

            </div>
            <div className="col-4">
                <div className="mb-4">
                <h2>Announcements</h2>
                    <img src="announcement.jpg" alt="Announcement" width="100%" height="auto"/>
                    <h4>Small Heading</h4>
                    <p>Name - Date and Time</p>
                </div>
                <div>
                    <h2>Activity</h2>
                    <img src="activity.jpg" alt="Activity" width="100%" height="auto"/>
                    <h4>Small Heading</h4>
                    <p>Name - Date and Time</p>
                </div>
            </div>
        </div>
    );
}

export default MainPage;