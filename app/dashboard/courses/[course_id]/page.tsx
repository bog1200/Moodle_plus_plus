export default async function Page({
                                       params,
                                   }: {
    params: Promise<{ course_id: string }>
}) {
    const courseId = (await params).course_id
    return <div>My Post: {courseId}</div>
}