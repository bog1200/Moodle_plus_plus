import {PutObjectCommand} from "@aws-sdk/client-s3";
import {s3Client} from "@/app/lib/cloud/r2";
import {redirect} from "next/navigation";

export default function FileUploadTest() {
async function upload(data: FormData) {
    'use server'

    const file: File | null = data.get('file') as unknown as File
    if (!file) {
        throw new Error('No file uploaded');
    }

    const bytes = await file.arrayBuffer();
    const buffer = Buffer.from(bytes);

    const uploadParams = {
        Bucket: process.env.S3_BUCKET!,
        Key: file.name,
        Body: buffer,
    };
       const upload =  await s3Client.send(new PutObjectCommand(uploadParams));
       if (upload.$metadata.httpStatusCode === 200) {
        return redirect('/files_test');
    }
       else {
    return redirect('/files_test?error=upload');
       }
}
                 

    return (
        <div className={"w-full"}>
            <form action={upload}>
                <input type="file" name="file" required/>
                <button type="submit">Upload</button>
            </form>
        </div>
    );

}