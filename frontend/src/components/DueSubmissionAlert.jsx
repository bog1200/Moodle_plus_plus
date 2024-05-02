import React from "react";
import uploadIcon from "bootstrap-icons/icons/upload.svg";

function DueSubmissionAlert({subject, text}) {
    return (
        <div className="d-flex align-items-center mb-3 bg-danger-subtle rounded-3">
            <img className="ms-2" src={uploadIcon} alt="Upload" width="30" height="30"/>
            <div className="ps-4">
                <h5>{subject}</h5>
                <p>{text}</p>
            </div>
        </div>
    );
}

export default DueSubmissionAlert;