import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import CoursesPage from "./components/pages/CoursesPage.jsx";
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import {createBrowserRouter, Outlet, RouterProvider} from "react-router-dom";
import MainPage from "./components/pages/MainPage.jsx";
import {AppAsideTitleComponent} from "./components/AppAsideTitleComponent.jsx";
import LoginPage from "./components/pages/LoginPage.jsx";
import SchedulePage from "./components/pages/SchedulePage.jsx";
import ProfilePage from "./components/pages/ProfilePage.jsx";
import SubjectPage from "./components/pages/SubjectPage.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        children: [
            {
                path: "/",
                element: <AppAsideTitleComponent />,
                children: [
                    {
                        path: "/dashboard",
                        element: <MainPage />
                    },
                    {
                        path: "/courses",
                        element: <CoursesPage />
                    },
                    {
                        path: "/schedule",
                        element: <SchedulePage />
                    },
                    {
                        path: "/profile",
                        element: <ProfilePage />
                    },
                    {
                        path: "/",
                        element: <MainPage />
                    },
                    {
                        path: `/subject/:subjectId`,
                        element: <SubjectPage />
                    }
                ]
            },
            {
                path: "/login",
                element: <LoginPage />

            }
        ],
    }
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
      <RouterProvider router={router}>
            <Outlet />
      </RouterProvider>
  </React.StrictMode>
)
