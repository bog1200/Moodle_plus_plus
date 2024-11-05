import React from "react";
import {auth} from "@/auth";
import {redirect} from "next/navigation";

export default async function FileTestLayout({children, list, upload}: {children: React.ReactNode, list: React.ReactNode, upload: React.ReactNode}) {
    const session = await auth();
    if (!session) {
        return redirect('/')
    }
    return (
        <div>
            <h1>Files</h1>
            {list}
            {children}
            {upload}
        </div>
    );
}