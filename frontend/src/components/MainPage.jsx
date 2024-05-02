import React from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";

function MainPage() {

    return (
        <div className="row">
            <div className="col-8">
                <h2>Timeline</h2>
                <div className="d-flex align-items-center mb-3">
                    <select className="form-select me-2">
                        <option selected>Choose...</option>
                        <option value="1">All</option>
                        <option value="2">Option 2</option>
                        <option value="3">Option 3</option>
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