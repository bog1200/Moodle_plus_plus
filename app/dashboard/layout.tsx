import Aside from "@/app/components/Aside";
import React from "react";

export default  async function DashboardLayout({ children }: { children: React.ReactNode }) {
    return (
        <div className={"lg:w-full flex min-h-screen"}>
            <div className={"fixed bottom-0 w-full h-[10%] lg:sticky lg:top-0 lg:left-0  lg:w-[20%] lg:h-screen z-10"}>
                <Aside />
            </div>
            <div className={"w-full lg:w-[80%] h-[80%] lg:h-full p-2 mb-[10%] pb-4  lg:mb-0   "}>
                {children}
            </div>
        </div>

    );
}