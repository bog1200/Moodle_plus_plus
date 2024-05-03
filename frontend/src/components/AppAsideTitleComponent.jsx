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
            <div className="col-xs-4 col-lg-1">
                <Aside/>
            </div>
            <div className="col-xs-8 col-lg-11">
                <PageTitleUp/>
                <Outlet/>
            </div>
        </div>
    </div>
    );
}