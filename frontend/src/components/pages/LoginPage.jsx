import React, { useState } from 'react';
import logo from '../../static/photos/logo.jpg';
import { useNavigate } from 'react-router-dom';

function LoginPage() {
    const [username, setUsername] = useState('');
    const [totp, setTotp] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        await fetch('https://mpp.romail.app/api/v1/account/totpLogin', {
            mode: 'cors',
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                username: username,
                totp: totp,
            }),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                if (data.accessToken) {
                    localStorage.setItem('accessToken', data.accessToken);
                    navigate('/');
                } else {
                    alert('Incorrect username or password');
                }
            });

    }
        return (
            <>
                <div className="d-flex justify-content-center pt-3">
                    <img src={logo} alt="Logo" className="mb-3" width={300} height= {250}/>
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
                                <label htmlFor="username" className="form-label">Username</label>
                                <input type="username" className="form-control" id="username"
                                       onChange={(event) => setUsername(event.target.value)}/>
                            </div>
                            <div className="mb-3">
                                <label htmlFor="password" className="form-label">Password</label>
                                <input type="password" className="form-control" id="password"
                                       onChange={event => setTotp(event.target.value)}/>
                            </div>
                            <div className="mb-3 form-check">

                            </div>
                            <button type="submit" className="btn btn-primary">Submit</button>
                        </form>
                        <br/>
                    </div>
                    <div className="col-4">
                        <p></p>
                    </div>
                </div>
            </>
        );
    }

export default LoginPage;


