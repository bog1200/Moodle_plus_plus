import { GetObjectCommand } from "@aws-sdk/client-s3";
import { getSignedUrl } from "@aws-sdk/s3-request-presigner";
import { s3Client } from "@/app/lib/cloud/r2";
import { auth } from "@/auth";

export async function getFile(fileLink: string, fileName?: string) {
    const session = await auth();
    if (!session) {
        throw new Error('Unauthorized');
    }
    console.log(fileName);

    const viewCommand = new GetObjectCommand({ Bucket: process.env.S3_BUCKET!, Key: fileLink });

    // const downloadCommand = new GetObjectCommand({
    //     Bucket: process.env.S3_BUCKET!,
    //     Key: fileLink!,
    //     ResponseContentDisposition: 'attachment; filename=' + fileName
    // });
    //
    const viewUrl = await getSignedUrl(s3Client, viewCommand, { expiresIn: 3600 });
    //const downloadUrl = await getSignedUrl(s3Client, downloadCommand, {expiresIn: 3600});

    return viewUrl;//, downloadUrl};
}