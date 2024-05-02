import React, { useState } from 'react';
import searchIcon from "bootstrap-icons/icons/search.svg";
import uploadIcon from "bootstrap-icons/icons/upload.svg";
import Course from '../Course.jsx';
import logo from '../../static/photos/logo.jpg';
import { useNavigate } from 'react-router-dom';

function LoginPage() {
    const [selectedOption, setSelectedOption] = useState(0);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSelectChange = (event) => {
        setSelectedOption(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        navigate('/');

        // try {
        //     const response = await axios.post('https://<spring-docker-container-name>/api/v1', {
        //         username: username,
        //         password: password
        //     });
        //
        //     if (response.data.success) {
        //         navigate('/');
        //     } else {
        //         // Handle incorrect username or password here
        //     }
        // } catch (error) {
        //     console.error('Error during API call', error);
        // }
    };

    return (
        <div>
            <div className="d-flex justify-content-center pt-3">
                <img src={logo} alt="Logo" className="mb-3"/>
            </div>

            <div className="row justify-content-center align-items-center pt-3">
                <div className="col-4">
                    <p></p>
                </div>
                <div
                    className="bg-dark-subtle col-4 align-content-center justify-content-center rounded-3 border-primary shadow-lg">
                    <h2 className="text-center">LOGIN</h2> <br/>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="email" className="form-label">Email address</label>
                            <input type="email" className="form-control" id="email" aria-describedby="emailHelp"/>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">Password</label>
                            <input type="password" className="form-control" id="password"/>
                        </div>
                        <div className="mb-3 form-check">
                            <input type="checkbox" className="form-check-input" id="remember"/>
                            <label className="form-check-label" htmlFor="remember">Remember me</label>
                        </div>
                        <button type="submit" className="btn btn-primary">Submit</button>
                    </form>
                    <br/>
                </div>
                <div className="col-4">
                    <p></p>
                </div>
            </div>
        </div>
    );
}

export default LoginPage;