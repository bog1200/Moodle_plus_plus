import React, {useEffect, useState} from 'react';
import './ProfilePage.css'
import {decodeToken, useJwt} from "react-jwt";


function ProfilePage() {
    const [profile, setProfile] = useState([]);
    // const [email, setEmail] = useState('');
    // const [firstName, setFirstName] = useState('');
    // const [lastName, setLastName] = useState('');
    const token = decodeToken(localStorage.getItem('accessToken'));
    const [studentId, setStudentId] = useState(token.sub);
    const [studentGroupId, setStudentGroupId] = useState('');
    const [id, setGroupId] = useState('');


    console.log("student id: ");
    console.log(studentId);




    useEffect(() => {
        let studentGroupId;


        const fetchProfile = async () => {
            const response = await fetch(`https://mpp.romail.app/api/v1/student/${studentId}`, {
                mode: 'cors',
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken')
                },
            });
            const data = await response.json();
            console.log(data);
            let initialProfile = {
                email: data.email,
                firstName: data.firstName,
                lastName: data.lastName,
                studentGroupId: data.studentGroupId,
            }
            console.log("group id of each student: ");
            setProfile(initialProfile);
            studentGroupId = initialProfile[0].studentGroupId;
        };
        fetchProfile().catch(error => console.error('Failed to fetch email, firstname and lastname of the student', error));


        const fetchProfileGroup = async () => {
            const response = await fetch(`https://mpp.romail.app/api/v1/students/group/${studentGroupId}`, {
                mode: 'cors',
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('accessToken'),
                },
            });
            const data = await response.json();
            console.log(data);
            let initialProfile = data.map(item => ({
                name: item.name,
            }));
            console.log("group id of each student: ");
            setProfile(initialProfile);
        };
        fetchProfile().catch(error => console.error('Failed to fetch group id', error));
    }, []);




    return (
        <>
            <div className="row">
                <div className="col-8">
                    <h2>Profile</h2><br/>
                    <div className="card bg-light mb-3">
                        <div className="card-body">
                            <h3 className="card-title">User details</h3>
                            {/*<p className="card-text"><strong>Email: </strong> {(event) => setEmail(event.target.value)}</p>*/}
                            <p className="card-text"><strong>Email: </strong>{profile.email}</p>
                            <p className="card-text"><strong>First name: </strong>{profile.firstName}</p>
                            <p className="card-text"><strong>Last name: </strong>{profile.lastName}</p>
                            <p className="card-text"><strong>Group: </strong>{profile.id}</p>

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

