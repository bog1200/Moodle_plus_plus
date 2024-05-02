import Products from "./components/Products";
import { useState, useEffect } from "react";
import Card from "./components/Card.jsx";
import Aside from "./components/Aside.jsx";
import MainPage from './components/pages/MainPage.jsx';
import PageTitleUp from "./components/PageTitleUp.jsx";
import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {createBrowserRouter, RouterProvider,} from "react-router-dom";
import { Outlet, Link } from "react-router-dom";
import {AppAsideTitleComponent} from "./components/AppAsideTitleComponent.jsx";



export default function App() {

    const [products, addProduct] = useState([]);

    return (
        <Outlet/>
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