import Aside from "./Aside.jsx";
import PageTitleUp from "./PageTitleUp.jsx";
import {Outlet} from "react-router-dom";
import * as React from "react";
import {useState} from "react";




//const [products, addProduct] = useState([]);



export function AppAsideTitleComponent() {
    return(
    <div className="container-fluid">
        <div className="row">
            <div className="col-1">
                <Aside/>
            </div>
            <div className="col-11">
                <PageTitleUp/>
                <Outlet/>
            </div>
        </div>
    </div>
    );
}