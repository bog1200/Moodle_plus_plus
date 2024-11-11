"use server";
import {s3Client} from "@/app/lib/cloud/r2";
import {DeleteObjectCommand} from "@aws-sdk/client-s3";
import {prisma} from "@/prisma";
import {redirect} from "next/navigation";

export async function deleteFile(data: FormData) {

    const q =  await s3Client.send(new DeleteObjectCommand({Bucket: process.env.S3_BUCKET!, Key: data.get('s3') as string}));
    if (q.$metadata.httpStatusCode === 204) {
        await prisma.file.delete({
            where: {
                id: data.get('db') as string
            }
        });
        return redirect('/files_test');
    }
    return redirect('/files_test?error=delete');

}