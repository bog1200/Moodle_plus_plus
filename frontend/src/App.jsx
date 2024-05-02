import Products from "./components/Products";
import { useState, useEffect } from "react";
import Card from "./components/Card.jsx";
import Aside from "./components/Aside.jsx";
import MainPage from './components/MainPage';
import PageTitleUp from "./components/PageTitleUp.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {createBrowserRouter, RouterProvider,} from "react-router-dom";



export default function App() {

    const [products, addProduct] = useState([]);

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-1">
                    <Aside/>
                </div>
                <div className="col-11">
                    <PageTitleUp/>
                    <MainPage/>
                </div>
            </div>
        </div>
    );
}








// const [isLoading, setIsLoading] = useState(false);
// useEffect(() => {
//   fetchProductHandler();
// }, []);

// async function fetchProductHandler() {
//   setIsLoading(true);
//
//   setIsLoading(false);
// }