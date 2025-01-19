
// import { getAllCourseDetails } from '@/app/actions/getAllCourseDetails';
// import {Assignment, Subject} from "@prisma/client";
import Link from "next/link";
import {getAssignmentById} from "@/app/actions/getAssignment";
import {SubmissionFileUpload} from '@/app/components/Upload';
import {DeleteSubmissionButton} from '@/app/components/DeleteSubmissionButton';
import {getFile} from "@/app/actions/getFile";
import {getUserRoles} from "@/app/actions/user";

export default async function AssignmentPage({
                                              params,
                                          }: {
    params: Promise<{ assignment_id: string, course_id: string }>
}) {
    const assignmentId = (await params).assignment_id;
    const courseId = (await params).course_id;
    const assignmentDetails = await getAssignmentById(assignmentId);
    const roles =  await getUserRoles();
    if (!roles) {
        throw new Error('Not authenticated');
    }
    if(assignmentDetails === null) {
        return <div>Assignment not found</div>;
    }
    console.log("ASSIGNMENT DETAILS : " + assignmentDetails)
    return (
        <div className="p-6 bg-background shadow-md rounded-lg">
            <Link href={`/dashboard/courses/${courseId}`} className = "mb-10">
                <button className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 ">Back</button>
            </Link>
            <br/>
            <h1 className="text-2xl text-foreground font-bold mb-4">{assignmentDetails.title}</h1>
            <p className="text-lg text-foreground mb-6">Description: {assignmentDetails.description}</p>
            <p className="text-lg text-foreground mb-6">Due Date: {assignmentDetails.dueDate.toLocaleDateString()}</p>
            <p className="text-lg text-foreground mb-6">Assignment Type: {assignmentDetails.assignmentType}</p>
            <h2 className="text-xl text-foreground font-semibold mb-4">Submissions</h2>
            {assignmentDetails.submissions.length === 0 ? (
                <div>
                    <h3>No submissions yet.</h3>
                    <SubmissionFileUpload assignmentID={assignmentId} courseID={courseId}/>
                {/*    send the assignment id */}
                </div>
            ) : (
                <ul className="space-y-4">
                    {assignmentDetails.submissions.map((submission) => (
                        <li key={submission.id}
                            className="p-4 bg-background rounded-lg shadow-sm border-foreground border-4">
                            <h3 className="text-lg font-semibold mt-2">Submission
                                Date: {new Date(submission.submissionDate).toLocaleDateString()}</h3>
                            {(roles.isAdmin || roles.isTeacher) && <>
                                <p className="text-lg text-foreground mt-6">Student
                                    Name: {submission.student.user.name}</p>
                                <p className="text-lg text-foreground mb-6">Student ID: {submission.student.id}</p>
                            </>


                            }
                            <p className="text-lg text-foreground mb-6">Text: {submission.text}</p>
                            <ul className="space-y-2">
                                {submission.files.map(async (file) => (
                                    <li key={file.id}
                                        className="p-2 bg-background rounded-lg shadow-sm border-foreground border-2">
                                        <h4 className="text-md font-semibold">File: {file.fileName}</h4>
                                        <p>Type: {file.fileType}</p>
                                        <p>Link: <a href={await getFile(file.fileLink, file.fileName)} target="_blank"
                                                    rel="noopener noreferrer">View File</a></p>
                                    </li>
                                ))}
                            </ul>
                            {roles.isStudent && <DeleteSubmissionButton submissionId={submission.id}/>}

                        </li>
                    ))}
                </ul>

                //     <ul className="space-y-4">
                //         {assignmentDetails.submissions.map(async (submission) => (
                //             <li key={file.id}
                //                 className="p-4 bg-background rounded-lg shadow-sm border-foreground border-4">
                //             <h3 className="text-lg font-semibold mt-2">Assignment: {submission.submissionDate.toLocaleDateString()}</h3>
                //             <div className="space-x-2">
                //                 <Link href={`/dashboard/courses/${courseId}/${assignmentId}/${submission.id}`}>
                //                     <button className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">View</button>
                //                 </Link>
                //                 <button onClick={async () => {
                //                     await deleteSubmission(submission.id);
                //                 }} className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">Delete</button>
                //
                //             </div>
                //         </li>
                //     ))}
                // </ul>
            )}


        </div>
    );
}
