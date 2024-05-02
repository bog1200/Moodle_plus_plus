import React from 'react';
import './Aside.css';

import mortarboardFill from 'bootstrap-icons/icons/mortarboard-fill.svg';
import calendarCheck from 'bootstrap-icons/icons/calendar-check.svg';
import calendarEventFill from 'bootstrap-icons/icons/calendar-event-fill.svg';
import lightbulbFill from 'bootstrap-icons/icons/lightbulb-fill.svg';
import personFill from 'bootstrap-icons/icons/person-fill.svg';
import logo from '../static/photos/logo.jpg';
import {Link} from "react-router-dom";
import coursesPage from "./pages/CoursesPage.jsx";
const Aside = () => {
    return (
        <aside>
            <div className="d-flex flex-column align-items-center py-3 bordered bg-secondary-subtle shadow-sm rounded-3">
                <br/>

                <img src={logo} alt="Logo" width="50" height="auto"/><br/>
                <Link to={'dashboard'} className="mb-4">
                    <img src={mortarboardFill} className="mb-4 fs-2" alt="mortarboard" width="30" height="30"/>
                </Link>
                <Link to={'courses'} className="mb-4">
                    <img src={calendarCheck} className="mb-4 fs-2" alt="calendar check" width="30" height="30"/>
                </Link>
                <Link to={'schedule'} className="mb-4">
                    <img src={calendarEventFill} className="mb-4 fs-2" alt="calendar event" width="30" height="30"/>
                </Link>
                <Link to={'profile'} className="mb-4">
                    <img src={personFill} className="mb-4 fs-2" alt="chat text" width="30" height="30"/>
                </Link>
                <br/><br/>
            </div>
            <hr/>
        </aside>
    );
};

export default Aside;