import Image from 'next/image';
import uploadIcon from 'bootstrap-icons/icons/upload.svg';


export function DueSubmissionAlert(props: {subject:string,text:string}) {
  return (
    <div className="flex items-center mb-3 bg-red-100 rounded-lg">
      <Image className="ml-2" src={uploadIcon} alt="Upload" width={30} height={30} />
      <div className="pl-4">
        <h5>{props.subject}</h5>
        <p>{props.text}</p>
      </div>
    </div>
  );
}