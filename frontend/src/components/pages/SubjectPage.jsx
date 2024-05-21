import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function SubjectPage() {
    const { subjectId } = useParams();
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

    return (
        <div>
            {/*<h2>{subjectInfo.name}</h2>*/}
            {/*<p>{subjectInfo.description}</p>*/}
            {/*// Display more subject information as needed*/}
        </div>
    );
}


export default SubjectPage;