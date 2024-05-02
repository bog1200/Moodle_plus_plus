import React from 'react';
import './Aside.css';

import mortarboardFill from 'bootstrap-icons/icons/mortarboard-fill.svg';
import calendarCheck from 'bootstrap-icons/icons/calendar-check.svg';
import calendarEventFill from 'bootstrap-icons/icons/calendar-event-fill.svg';
import lightbulbFill from 'bootstrap-icons/icons/lightbulb-fill.svg';
import chatLeftText from 'bootstrap-icons/icons/chat-left-text.svg';
import logo from '../static/photos/logo.jpg';
const Aside = () => {
    return (
        <aside>
            <div className="d-flex flex-column align-items-center py-3 bordered">
                <br/>

                <img src={logo} alt="Logo" width="50" height="auto"/><br/>
                <a href="#" className="mb-4">
                    <img src={mortarboardFill} className="mb-4 fs-2" alt="mortarboard" width="30" height="30"/>
                </a>
                <a href="#" className="mb-4">
                    <img src={calendarCheck} className="mb-4 fs-2" alt="calendar check" width="30" height="30"/>
                </a>
                <a href="#" className="mb-4">
                    <img src={calendarEventFill} className="mb-4 fs-2" alt="calendar event" width="30" height="30"/>
                </a>
                <a href="#" className="mb-4">
                    <img src={lightbulbFill} className="mb-4 fs-2" alt="lightbulb" width="30" height="30"/>
                </a>
                <a href="#" className="mb-4">
                    <img src={chatLeftText} className="mb-4 fs-2" alt="chat text" width="30" height="30"/>
                </a>
                <br/><br/>
            </div>
            <hr/>
        </aside>
    );
};

export default Aside;