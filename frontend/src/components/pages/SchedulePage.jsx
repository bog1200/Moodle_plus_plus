import React, { useState, useEffect } from 'react';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';
import './SchedulePage.css';

function SchedulePage() {
    const [dueDates, setDueDates] = useState([
        { date: new Date(2024, 4, 15), homework: 'Math: Chapter 5' },
        { date: new Date(2024, 4, 20), homework: 'English: Essay' },
        // Add more due dates as needed
    ]);

    useEffect(() => {
        // Fetch the due dates from the server and update the state
        // setDueDates(fetchedDueDates);
    }, []);

    return (
        <div className="row">
            <div className="col-12">
                <h2>Schedule</h2>
                <Calendar
                    tileContent={({ date, view }) => {
                        if (view === 'month') {
                            const dueDate = dueDates.find(dueDate =>
                                dueDate.date.getDate() === date.getDate() &&
                                dueDate.date.getMonth() === date.getMonth() &&
                                dueDate.date.getFullYear() === date.getFullYear()
                            );
                            return (
                                <div>
                                    <p>Day {date.getDate()}</p>
                                    {dueDate && <p style={{ color: 'red' }}>{dueDate.homework}</p>}
                                </div>
                            );
                        }
                    }}
                />
            </div>
        </div>
    );
}

export default SchedulePage;