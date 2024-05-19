// eslint-disable-next-line no-unused-vars
import React from 'react';
import './Course.css';
import {Link} from "react-router-dom";

// eslint-disable-next-line react/prop-types
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