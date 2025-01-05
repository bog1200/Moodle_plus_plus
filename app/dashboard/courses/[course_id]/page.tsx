
import { getAllCourseDetails } from '@/app/actions/getAllCourseDetails';
import {Assignment, Subject} from "@prisma/client";
import Link from "next/link";

export default async function SubjectPage({
                                       params,
                                   }: {
    params: Promise<{ course_id: string }>
}) {
    const courseId = (await params).course_id;
    const courseDetails = await getAllCourseDetails(courseId);
    if(courseDetails === null) {
        return <div>Course not found</div>;
    }
    console.log("courseDetails: " + courseDetails.assignments);

    return (
        <div className="p-6 bg-background shadow-md rounded-lg">
            <h1 className="text-2xl text-foreground font-bold mb-4">{courseDetails.name}</h1>
            <p className="text-lg text-foreground mb-6">Teacher: {courseDetails.teacher.user.name}</p>
            <h2 className="text-xl text-foreground font-semibold mb-4">Assignments</h2>
            <ul className="space-y-4">
                {courseDetails.assignments.map((assignment: Assignment) => (
                    <li key={assignment.id} className="p-4 bg-background rounded-lg shadow-sm border-foreground border-4">
                        <h3 className="text-lg font-semibold mt-2">{assignment.title}</h3>
                        <p className="text-gray-600">{assignment.description}</p>
                        <p className="text-gray-500">Due Date: {new Date(assignment.dueDate).toLocaleDateString()}</p>
                        <Link href={`/dashboard/courses/${courseId}/${assignment.id}`}>
                            <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">View</button>
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}
