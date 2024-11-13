import AsideDesktop from "@/app/components/Aside";
import React from "react";

export default  async function DashboardLayout({ children }: { children: React.ReactNode }) {
    return (
        <div className={"h-full w-full flex max-h-full max-w-full"}>
            <div className={"hidden lg:block h-screen sticky top-0 left-0 w-64"}>
                <AsideDesktop />
            </div>
            <div className={"w-full h-full p-2 mb-24 lg:mb-0"}>
                {children}
            </div>

            <div className={"fixed bottom-0 w-screen h-24 bg-gray-300 lg:hidden"}>
              <AsideDesktop />

            </div>
        </div>

    );
}