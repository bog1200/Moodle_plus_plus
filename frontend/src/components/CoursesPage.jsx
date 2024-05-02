import React, { useState } from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";
import Course from './Course';

function CoursesPage() {
    const [selectedOption, setSelectedOption] = useState(0);

    const handleSelectChange = (event) => {
        setSelectedOption(event.target.value);
    };

    return (
        <div className="row">
            <div className="col-8">
                <h2>Timeline</h2>
                <div className="d-flex align-items-center mb-3">
                    <select className="form-select me-2" onChange={handleSelectChange}>
                        <option value="0">Choose...</option>
                        <option value="3">Option 1</option>
                        <option value="5">Option 2</option>
                        <option value="8">Option 3</option>
                    </select>
                    <input type="search" className="form-control me-2" placeholder="Search..."/>
                    <img src={searchIcon} alt="Search" width="30" height="30"/>
                </div>
                <div className="d-flex align-items-center mb-3">
                    <img className="ms-2" src={uploadIcon} alt="Upload" width="30" height="30"/>
                    <div className="ps-4">
                        <h3>Title</h3>
                        <p>Paragraph</p>
                    </div>
                </div>
                {Array.from({ length: selectedOption }).map((_, index) => (
                    <Course key={index} />
                ))}
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

export default CoursesPage;