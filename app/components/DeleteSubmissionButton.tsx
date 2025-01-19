"use client";
import {deleteSubmission} from "@/app/actions/submission";
import {useRouter} from "next/navigation";

export function DeleteSubmissionButton({submissionId}: {submissionId: string}) {
    const router = useRouter();
    console.log("SUBMISSION ID: " + submissionId);
    return (

        <button className="bg-red-500 text-white px-4 py-2 my-2 rounded hover:bg-red-600" onClick={async () =>
        {
            await deleteSubmission(submissionId);
            router.refresh();
        }}>Delete
        </button>
    );
}