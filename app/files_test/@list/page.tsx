import {s3Client} from "@/app/lib/cloud/r2";
import {ListObjectsV2Command, GetObjectCommand, DeleteObjectCommand} from "@aws-sdk/client-s3";
import {getSignedUrl} from "@aws-sdk/s3-request-presigner";
import {redirect} from "next/navigation";

export default async function FileListTest() {

    const data = await s3Client.send(new ListObjectsV2Command({Bucket: process.env.S3_BUCKET!}));
    const filesWithUrls = await Promise.all(
        data.Contents?.map(async (file) => {
            const command = new GetObjectCommand({Bucket: process.env.S3_BUCKET!, Key: file.Key!});
            const url = await getSignedUrl(s3Client, command, {expiresIn: 3600});
            return {key: file.Key!, url, s3: file.Key};
        }) || []
    );

    async function deleteFile(data: FormData) {
        "use server"
       const q =  await s3Client.send(new DeleteObjectCommand({Bucket: process.env.S3_BUCKET!, Key: data.get('key') as string}));
        if (q) {
            return redirect('/files_test');
        }
        return redirect('/files_test?error=delete');

    }


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
                    <tr key={file.key}>
                        <td>{file.key}</td>
                        <td><a href={
                            file.url
                        }>View</a></td>
                        <td><a href={
                            file.url
                        } download>Download</a></td>
                        <td>
                            { file.key === '3.mp3' ? <p>Cannot delete 3.mp3</p> :
                            <form action={deleteFile}>
                                <input type="hidden" name="key" value={file.key}/>
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