import {deleteFile} from "@/app/actions/deleteFile";
import {getAllFiles} from "@/app/actions/getAllFiles";



export default async function FileListTest() {

    const filesWithUrls = await getAllFiles();



    return (
        <div className={"w-full"}>
            <p>Click on a file to download:</p>
            <table className={"w-full text-center"}>
                <thead>
                <tr>
                    <th className={"w-1/4"}>File Name</th>

                    <th className={"w-1/4"}>Owner</th>
                    <th className={"w-1/4"}>View/Download</th>
                    <th className={"w-1/4"}>Delete</th>
                </tr>
                </thead>
                <tbody>
                {filesWithUrls.map((file) => (
                    <tr key={file.id}>
                        <td>{file.name}</td>
                        <td className={"hover:underline"}><a href={`/dashboard/user_manager/${file.owner}`}>{file.owner}</a> </td>
                        <td>
                            <a className="mr-2" href={file.viewUrl} target="_blank">View</a>
                            <a href={file.downloadUrl} target="_blank">Download</a></td>
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