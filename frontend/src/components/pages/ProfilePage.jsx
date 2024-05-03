import React from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";

function ProfilePage() {



    return (
        <div className="row">
            <div className="col-8">
                <h2>Profile</h2><br/>
                <div className="card bg-light mb-3">
                    <div className="card-body">
                        <h3 className="card-title">Heading 1</h3>
                        <p className="card-text">Paragraph 1</p>
                        <p className="card-text">Paragraph 2</p>
                    </div>
                </div>
                <div className="card bg-light mb-3">
                    <div className="card-body">
                        <h3 className="card-title">Heading 2</h3>
                        <p className="card-text">Paragraph 1</p>
                        <p className="card-text">Paragraph 2</p>
                    </div>
                </div>
                <div className="card bg-light mb-3">
                    <div className="card-body">
                        <h3 className="card-title">Heading 3</h3>
                        <p className="card-text">Paragraph 1</p>
                        <p className="card-text">Paragraph 2</p>
                    </div>
                </div>
            </div>
            <div className="col-4">
            </div>
        </div>
    );
}

export default ProfilePage;