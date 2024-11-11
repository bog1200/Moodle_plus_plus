import {s3Client} from "@/app/lib/cloud/r2";
import {GetObjectCommand} from "@aws-sdk/client-s3";
import {getSignedUrl} from "@aws-sdk/s3-request-presigner";
import {prisma} from "@/prisma";
import {deleteFile} from "@/app/actions/deleteFile";

export default async function FileListTest() {

    const data = await prisma.file.findMany();
    const filesWithUrls = await Promise.all(
        data.map(async (file) => {
            const viewCommand = new GetObjectCommand({Bucket: process.env.S3_BUCKET!, Key: file.fileLink!});
            const downloadCommand = new GetObjectCommand({Bucket: process.env.S3_BUCKET!, Key: file.fileLink!, ResponseContentDisposition: 'attachment; filename=' + file.fileName});
            const viewUrl = await getSignedUrl(s3Client, viewCommand, {expiresIn: 3600});
            const downloadUrl = await getSignedUrl(s3Client, downloadCommand, {expiresIn: 3600});
            return {id: file.id, name: file.fileName!, viewUrl, downloadUrl, s3: file.fileLink};
        }) || []
    );




    return (
        <div className={"w-full"}>
            <p>Click on a file to download:</p>
            <table className={"w-full text-center"}>
                <thead>
                <tr>
                    <th className={"w-1/4"}>File Name</th>
                    <th className={"w-1/4"}>View</th>
                    <th className={"w-1/4"}>Download</th>
                    <th className={"w-1/4"}>Delete</th>
                </tr>
                </thead>
                <tbody>
                {filesWithUrls.map((file) => (
                    <tr key={file.id}>
                        <td>{file.name}</td>
                        <td><a href={
                            file.viewUrl
                        } target="_blank">View</a></td>
                        <td><a href={
                            file.downloadUrl
                        } target="_blank">Download</a></td>
                        <td>
                            { file.name === '3.mp3' ? <p>Cannot delete 3.mp3</p> :
                            <form action={deleteFile}>
                                <input type="hidden" name="db" value={file.id}/>
                                <input type="hidden" name="s3" value={file.s3}/>
                                <button type="submit">Delete</button>
                            </form>
                            }
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>

        </div>
    );
}