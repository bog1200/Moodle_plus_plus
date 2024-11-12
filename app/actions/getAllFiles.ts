import {GetObjectCommand} from "@aws-sdk/client-s3";
import {getSignedUrl} from "@aws-sdk/s3-request-presigner";
import {s3Client} from "@/app/lib/cloud/r2";
import {prisma} from "@/prisma";
import {auth} from "@/auth";

export async function getAllFiles() {
    const session = await auth();
    if (!session) {
        throw new Error('Unauthorized');
    }
    const data = await prisma.file.findMany();
    return await Promise.all(
        data.map(async (file) => {
            const viewCommand = new GetObjectCommand({Bucket: process.env.S3_BUCKET!, Key: file.fileLink!});
            const downloadCommand = new GetObjectCommand({
                Bucket: process.env.S3_BUCKET!,
                Key: file.fileLink!,
                ResponseContentDisposition: 'attachment; filename=' + file.fileName
            });
            const viewUrl = await getSignedUrl(s3Client, viewCommand, {expiresIn: 3600});
            const downloadUrl = await getSignedUrl(s3Client, downloadCommand, {expiresIn: 3600});
            return {id: file.id, name: file.fileName!, viewUrl, downloadUrl, s3: file.fileLink};
        }) || []
    );
}