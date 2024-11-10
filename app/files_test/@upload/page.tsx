"use client";
// import {PutObjectCommand} from "@aws-sdk/client-s3";
// import {s3Client} from "@/app/lib/cloud/r2";
import {FileSummary, getUploadKey} from "@/app/actions/getUploadKey";
import {ChangeEvent, useState} from "react";
import {redirect} from "next/navigation";

export default function FileUploadTest() {

    const [uploadKey, setUploadKey] = useState<string | null>(null);

   async function preUpload(event: ChangeEvent ) {
        event.preventDefault();
        const file: File | null | undefined = (event.target as HTMLInputElement).files?.item(0);
        if (!file) {
            throw new Error('No file uploaded');
        }
        else {
            const fileSummary: FileSummary = {
                name: file.name,
                type: file.type,
                size: file.size
            }
            const data = await getUploadKey(fileSummary);
            console.log(data);
            setUploadKey(data);
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
            return redirect('/files_test');
        }
        else {
            return redirect('/files_test?error=upload');
        }
    }




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