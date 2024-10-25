import Image from 'next/image';
import uploadIcon from 'bootstrap-icons/icons/upload.svg';

interface DueSubmissionAlertProps {
  subject: string;
  text: string;
}

const DueSubmissionAlert: React.FC<DueSubmissionAlertProps> = ({ subject, text }) => {
  return (
    <div className="flex items-center mb-3 bg-red-100 rounded-lg">
      <Image className="ml-2" src={uploadIcon} alt="Upload" width={30} height={30} />
      <div className="pl-4">
        <h5>{subject}</h5>
        <p>{text}</p>
      </div>
    </div>
  );
};

export default DueSubmissionAlert;