// eslint-disable-next-line no-unused-vars
import React from 'react';
import searchIcon from 'bootstrap-icons/icons/search.svg';
import bellIcon from 'bootstrap-icons/icons/bell.svg';
import gearIcon from 'bootstrap-icons/icons/gear.svg';

const PageTitleUp = () => {
    return (
        <div className="d-flex justify-content-between align-items-center py-3">
            <h1>Index</h1>
            <div className="d-flex align-items-center">
                <input type="search" className="form-control me-2" placeholder="Search..."/>
                <img src={searchIcon} alt="Search" width="30" height="30"/>
                <img src={bellIcon} alt="Notifications" width="30" height="30" className="ms-2"/>
                <img src={gearIcon} alt="Settings" width="30" height="30" className="ms-2"/>
            </div>
        </div>
    );
};

export default PageTitleUp;