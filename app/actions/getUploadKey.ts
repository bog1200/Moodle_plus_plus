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
    const fileSizeLimit = parseInt(process.env.NEXT_PUBLIC_FILE_UPLOAD_MAX_SIZE!);

    if (file.size > fileSizeLimit) {
        // Client side validation should prevent this from happening, but client side validation can be bypassed
        throw new Error('File is too large. Maximum size is ' + fileSizeLimit + ' bytes.');
    }
    const params = {
        Bucket: process.env.S3_BUCKET!,
        Key: file.name,
        ContentType: file.type
    };
    return await getSignedUrl(s3Client, new PutObjectCommand(params), {expiresIn: 900});


}