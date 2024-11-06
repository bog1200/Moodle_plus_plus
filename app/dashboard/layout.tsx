import Aside from "@/app/components/Aside";

export default  async function DashboardLayout({ children }: { children: React.ReactNode }) {
    return (
        <>
            <Aside />
            {children}
        </>

    );
}