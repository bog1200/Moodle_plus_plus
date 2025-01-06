"use client";
import {deleteSubmission} from "@/app/actions/submission";
export function DeleteSubmissionButton({file}: { file: { id: string, fileName: string, fileType: string, fileLink: string } }, courseID?: string, assignmentID?: string) {
    return (
        <button onClick={async () => {
            await deleteSubmission(file.id, courseID, assignmentID);
        }} className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">Delete
        </button>
    );
}