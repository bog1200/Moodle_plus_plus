import React from 'react';
import {useNavigate} from "react-router-dom";

async function ProfilePage() {
    const [email, setEmail] = React.useState('');
    const [fistName, setName] = React.useState('');
    const [lastName, setLastName] = React.useState('');
    const [id, setGroupId] = React.useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

       //TODO: Read the token from the local storage
        const accessToken = localStorage.getItem('accessToken');

        await fetch('https://mpp.romail.app/api/v1/students', {
            mode: 'cors',
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.id) {
                    localStorage.setItem('id', data.id);
                    navigate('/');
                }
            });
        }

        await fetch('https://mpp.romail.app/api/v1/students/group', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                group_id: id,
            }),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.id) {
                    localStorage.setItem('id', data.id);
                    navigate('/');
                }
            });
    }


    return (
        <>
        <div className="row">
            <div className="col-8">
                <h2>Profile</h2><br/>
                <div className="card bg-light mb-3">
                    <div className="card-body">
                        <h3 className="card-title">User details</h3>
                        <p className="card-text"><strong>Email: </strong></p>
                        {event => setEmail(event.target.value)}
                        <p className="card-text"><strong>First name: </strong></p>
                        {event => setName(event.target.value)}
                        <p className="card-text"><strong>Last name: </strong> </p>
                        {event => setLastName(event.target.value)}
                        <p className="card-text"><strong>Group: </strong></p>
                        {event => setGroupId(event.target.value)}
                    </div>
                </div>
            </div>
            <div className="col-4">
            </div>
        </div>
        </>
    );
}

export default ProfilePage;