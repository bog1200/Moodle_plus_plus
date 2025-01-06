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

export default function FileUploadTest(props: {assignmentID: string}) {

    const [uploadKey, setUploadKey] = useState<string | null>(null);
    const [fileName, setFileName] = useState<string | null>(null);
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
            const userId = await getCurrentStudent();
            if(userId === null) {throw new Error('userId not found');}
            await createAssignmentSubmission(userId.id, props.assignmentID, uploadData.id);
            //return redirect('/dashboard/file_manager/');
        }
        else {
            return redirect('/dashboard/file_manager/?error=upload'); //TODO
        }
    }


//query the database to tie the file to the assignment (we need the AssignmentId

//     async function upload(data: FormData) {
//     'use server'
//
//     const file: File | null = data.get('file') as unknown as File
//     if (!file) {
//         throw new Error('No file uploaded');
//     }
//
//     const bytes = await file.arrayBuffer();
//     const buffer = Buffer.from(bytes);
//
//     const uploadParams = {
//         Bucket: process.env.S3_BUCKET!,
//         Key: file.name,
//         Body: buffer,
//         ContentType: file.type
//     };
//        const upload =  await s3Client.send(new PutObjectCommand(uploadParams));
//        if (upload.$metadata.httpStatusCode === 200) {
//         return redirect('/files_test');
//     }
//        else {
//     return redirect('/files_test?error=upload');
//        }
// }


    return (
        <div className={"w-full"}>
            <form action={upload}>
                <input type="file" name="file" onChange={preUpload} required/>
                <button type="submit">Upload</button>
            </form>
        </div>
    );

}