import React from "react";

export default function FileTestLayout({children, list, upload}: {children: React.ReactNode, list: React.ReactNode, upload: React.ReactNode}) {
    return (
        <div>
            <h1>Files</h1>
            {list}
            {children}
            {upload}
        </div>
    );
}