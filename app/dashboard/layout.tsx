import Aside from "@/app/components/Aside";

export default  async function DashboardLayout({ children }: { children: React.ReactNode }) {
    return (
        <div className={"w-full flex"}>
            <div className={"sticky w-[10%]"}>
                <Aside />
            </div>
            <div className={"w-[90%] p-2"}>
                {children}
            </div>
        </div>

    );
}