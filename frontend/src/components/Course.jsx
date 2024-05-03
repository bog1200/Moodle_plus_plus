// course title, description, and price
// then i will call the API and populate the data in the Course component

// Course.jsx
import React from 'react';
import './Course.css';
import {Link} from "react-router-dom";


function Course({ name, professor, subject }) {
    return (
        <Link to={`/subject/${subject}`} style={{ textDecoration: 'none', color: 'inherit' }}>
        <div className="course_div align-items-center mb-3 rounded-3 bg-primary bg-opacity-10">
            <h2>{name}</h2>
            <p>{professor}</p>
        </div>
        </Link>
    );
}

export default Course;