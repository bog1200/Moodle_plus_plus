"use server";
import {getSignedUrl} from "@aws-sdk/s3-request-presigner";
import {s3Client} from "@/app/lib/cloud/r2";
import {PutObjectCommand} from "@aws-sdk/client-s3";
import {auth} from "@/auth";
export interface FileSummary{
    link?: string;
    name: string;
    type: string;
    size: number;
}
export async function getUploadKey(file: FileSummary): Promise<string[]> {
    const session = await auth();
    const S3_FOLDER = "mpp";
    if (!session) {
        throw new Error('Not authenticated');
    }

    const fileSizeLimit = parseInt(process.env.NEXT_PUBLIC_FILE_UPLOAD_MAX_SIZE!);

    if (file.size > fileSizeLimit) {
        // Client side validation should prevent this from happening, but client side validation can be bypassed
        throw new Error('File is too large. Maximum size is ' + fileSizeLimit + ' bytes.');
    }
    const file_location = `${S3_FOLDER}/${session.user!.id}/${Date.now()}-${file.name}`;
    const params = {
        Bucket: process.env.S3_BUCKET!,
        Key: file_location,
        ContentType: file.type
    };
    const url = await getSignedUrl(s3Client, new PutObjectCommand(params), {expiresIn: 900});

    return [url, file_location];


}