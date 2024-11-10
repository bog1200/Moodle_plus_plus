"use server";
import {getSignedUrl} from "@aws-sdk/s3-request-presigner";
import {s3Client} from "@/app/lib/cloud/r2";
import {PutObjectCommand} from "@aws-sdk/client-s3";
export interface FileSummary{
    name: string;
    type: string;
    size: number;
}
export async function getUploadKey(file: FileSummary): Promise<string> {
    const params = {
        Bucket: process.env.S3_BUCKET!,
        Key: file.name,
        ContentType: file.type
    };
    return await getSignedUrl(s3Client, new PutObjectCommand(params), {expiresIn: 900});


}