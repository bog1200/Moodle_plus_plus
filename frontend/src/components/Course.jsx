// course title, description, and price
// then i will call the API and populate the data in the Course component

// Course.jsx
import React from 'react';
import './Course.css';


function Course({ name, professor }) {
    return (
        <div className="course_div align-items-center mb-3 rounded-3 bg-primary bg-opacity-10">
            <h2>{name}</h2>
            <p>{professor}</p>
        </div>
    );
}

export default Course;