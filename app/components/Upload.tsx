"use client";
// import {PutObjectCommand} from "@aws-sdk/client-s3";
// import {s3Client} from "@/app/lib/cloud/r2";
import {FileSummary, getUploadKey} from "@/app/actions/getUploadKey";
import {ChangeEvent, useState} from "react";
import {redirect} from "next/navigation";
import {createAssignmentSubmission} from "@/app/actions/assignmentSubmissionUpload";
import {storeUpload} from "@/app/actions/storeUpload";
import {getCurrentStudent} from "@/app/actions/getCurrentStudent";
import {getUserId} from "@/app/actions/getUserId";
//import {storeUpload} from "@/app/actions/storeUpload";

export function SubmissionFileUpload(props: {assignmentID: string, courseID: string}) {

    const [uploadKey, setUploadKey] = useState<string | null>(null);
    const [fileName, setFileName] = useState<string | null>(null);
    const [text, setText] = useState<string>('');
    const fileSizeLimit = parseInt(process.env.NEXT_PUBLIC_FILE_UPLOAD_MAX_SIZE!);


    async function preUpload(event: ChangeEvent ) {
        event.preventDefault();
        const file: File | null | undefined = (event.target as HTMLInputElement).files?.item(0);
        if (file) {
            if (file.size > fileSizeLimit) {
                alert(`File is too large. Maximum size is ${fileSizeLimit} bytes.`);
                // clear the input
                (event.target as HTMLInputElement).value = '';
            } else {
                const fileSummary: FileSummary = {
                    name: file.name,
                    type: file.type,
                    size: file.size
                }
                console.log("Creating upload key");
                const data = await getUploadKey(fileSummary);
                console.log(data);
                setUploadKey(data[0]);
                setFileName(data[1]);
            }
        }
    }

    async function upload(formData: FormData) {
        const file: File | null = formData.get('file') as unknown as File
        if (!file) {
            throw new Error('No file uploaded');
        }
        if (!uploadKey) {
            throw new Error('No upload key');
        }
        const fileSummary: FileSummary = {
            name: file.name,
            type: file.type,
            size: file.size,
            link: fileName!
        }
        const bytes = await file.arrayBuffer();
        const buffer = Buffer.from(bytes);
        const upload = await fetch(uploadKey!, {
            method: 'PUT',
            body: buffer,
            headers: {
                'Content-Type': file.type
            }
        });
        if (upload.ok) {
            const uploadData = await storeUpload(fileSummary);
            const studentId = await getCurrentStudent();
            if(studentId === null) {throw new Error('userId not found');}
            const userId = await getUserId();
            if(userId === null) {throw new Error('userId not found');}
            await createAssignmentSubmission(studentId.id, props.assignmentID, uploadData.id, userId.id, text);
            return redirect(`/dashboard/courses/${props.courseID}/${props.assignmentID}`);
        }
        else {
            return redirect('/dashboard/file_manager/?error=upload'); //TODO
        }
    }


    return (
        <div className={"w-full"}>
            <form action={upload}>
                <input type="text" name="text" placeholder="text" required onChange={(e) => setText(e.target.value)}/>
                <input type="file" name="file" onChange={preUpload} required/>
                <button type="submit">Upload</button>
            </form>
        </div>
    );

}

