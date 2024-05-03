import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function SubjectPage() {
    const { subject } = useParams();
    const [subjectInfo, setSubjectInfo] = useState(null);

    // useEffect(() => {
    //     // Fetch the subject's information from your server or state
    //     // Replace with your actual API endpoint
    //
    //     // sau pun cu environment variable si il schimb peste tot
    //     //${process.env.REACT_APP_API_URL}
    //     fetch(`https://localhost:8080/subjects/${encodeURIComponent(subject)}`)
    //         .then(response => response.json())
    //         .then(data => setSubjectInfo(data));
    // }, [subject]);

    if (!subjectInfo) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h2>{subjectInfo.name}</h2>
            <p>{subjectInfo.description}</p>
            {/*// Display more subject information as needed*/}
        </div>
    );
}

export default SubjectPage;