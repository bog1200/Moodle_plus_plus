
import { getAllCourseDetails } from '@/app/actions/getAllCourseDetails';
import {Assignment, Subject} from "@prisma/client";
import Link from "next/link";
import {getAssignmentById} from "@/app/actions/getAssignment";
import {deleteSubmission} from "@/app/actions/submission";

export default async function AssignmentPage({
                                              params,
                                          }: {
    params: Promise<{ assignment_id: string, course_id: string }>
}) {
    const assignmentId = (await params).assignment_id;
    const courseId = (await params).course_id;
    const assignmentDetails = await getAssignmentById(assignmentId);
    if(assignmentDetails === null) {
        return <div>Assignment not found</div>;
    }

    return (
        <div className="p-6 bg-background shadow-md rounded-lg">
            <h1 className="text-2xl text-foreground font-bold mb-4">{assignmentDetails.title}</h1>
            <p className="text-lg text-foreground mb-6">Description: {assignmentDetails.description}</p>
            <p className="text-lg text-foreground mb-6">Due Date: {assignmentDetails.dueDate.toLocaleDateString()}</p>
            <p className="text-lg text-foreground mb-6">Assignment Type: {assignmentDetails.assignmentType}</p>
            <h2 className="text-xl text-foreground font-semibold mb-4">Submissions</h2>
            <ul className="space-y-4">
                {assignmentDetails.submissions.map(async (submission) => (
                    <li key={submission.id}
                        className="p-4 bg-background rounded-lg shadow-sm border-foreground border-4">
                        <h3 className="text-lg font-semibold mt-2">Assignment: {submission.submissionDate.toLocaleDateString()}</h3>
                        <div className="space-x-2">
                            <Link href={`/dashboard/courses/${courseId}/${assignmentId}/${submission.id}`}>
                                <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">View</button>
                            </Link>
                            <button onClick={async () => {
                                await deleteSubmission(submission.id);
                            }} className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">Delete</button>

                        </div>
                    </li>
                ))}
            </ul>


        </div>
    );
}
