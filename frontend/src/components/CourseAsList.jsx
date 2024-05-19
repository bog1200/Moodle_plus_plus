// course title, description, and price
// then i will call the API and populate the data in the Course component

// Course.jsx
// eslint-disable-next-line no-unused-vars
import React from 'react';
import './CourseAsList.css';


// eslint-disable-next-line react/prop-types
function CourseAsList({ name, professor }) {
    return (
        <div className="courseList_div align-items-center rounded-3 bg-primary bg-opacity-10">
            <h4>{name}</h4>
            <p>{professor}</p>
        </div>
    );
}

export default CourseAsList;