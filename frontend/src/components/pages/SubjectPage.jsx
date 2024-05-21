import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function SubjectPage() {
    const { subjectId } = useParams();

    const [loading, setLoading] = useState(true);
    const [subjectInfo, setSubjectInfo] = useState(null);

    useEffect( () => {
        // Fetch the subject's information from your server or state
        // Replace with your actual API endpoint
        console.log("SID: "+subjectId)
        // sau pun cu environment variable si il schimb peste tot
        //${process.env.REACT_APP_API_URL}
        // fetch(`https://mpp.romail.app/api/v1/subject/${subjectId}`, {
        //     mode: 'cors',
        //     method: 'GET',
        // })
        //     .then(response => response.json())
        //     .then(data => {
        //         setSubjectInfo(data);
        //     });
    });

    const [subjectAPI, setsubjectAPI] = useState(null);
    useEffect(() => {
        const fetchSubjectAPI = async() => {
            //let initialCourses = [];
            const response = await fetch('https://mpp.romail.app/api/v1/assignment/subject/'+subjectId, {
                mode: 'cors',
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': "Bearer " + localStorage.getItem('accessToken')
                },
            });
            console.log('https://mpp.romail.app/api/v1/assignment/subject/'+subjectId)
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const data = await response.json();
            console.log(data);
            let initialSubjectAPI = data.map(item => ({
                type: item.type,
                name: item.name,
                description: item.description,
                startDate: item.startDate,
                endDate: item.endDate
            }));
            console.log("initial courses array: ");
            console.log(initialSubjectAPI);
            setsubjectAPI(initialSubjectAPI);
            setLoading(false);
            }
        fetchSubjectAPI().catch(error => console.error('Failed to fetch subjects:', error));
        })

    //2

        // const [assignmentsAPI, setassignmentsAPI] = useState(null);
        // useEffect(() => {
        //     const fetchAssignmentsAPI = async() => {
        //         //let initialCourses = [];
        //         const response = await fetch('https://mpp.romail.app/api/v1/assignment/subject/'+subjectId, {
        //             mode: 'cors',
        //             method: 'GET',
        //             headers: {
        //                 'Content-Type': 'application/json',
        //                 'Authorization': "Bearer " + localStorage.getItem('accessToken')
        //             },
        //         });
        //         const data = await response.json();
        //         console.log(data);
        //         let initialSubjectAPI = data.map(item => ({
        //             name: item.name
        //         }));
        //         console.log("initial courses array: ");
        //         console.log(initialSubjectAPI);
        //         setsubjectAPI(initialSubjectAPI);
        //     }
        //     fetchAssignmentsAPI().catch(error => console.error('Failed to fetch assignments:', error));
        // })

    let subjectAPILength = 0;
    if (subjectAPI) {
        subjectAPILength = subjectAPI.length;
    }
    console.log(subjectAPILength);

    return (
        <div>
            <h3>List of assignments for this subject: </h3>
            {subjectAPILength === 0 ? (
                <p>There are no assignments for this subject currently.</p>
            ) : subjectAPI.map((item, index) => (
                <p key={index}>
                    Type: {item.type} <br/>
                    Name: {item.name} <br/>
                    Description: {item.description} <br/>
                </p>
            ))}

        </div>
    );
}


export default SubjectPage;