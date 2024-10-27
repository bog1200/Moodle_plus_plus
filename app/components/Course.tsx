// import Image from 'next/image';
import Link from 'next/link';

interface CourseProps {
  name: string;
  professor: string;
  subject: string;
}

const Course: React.FC<CourseProps> = ({ name, professor, subject }) => {
  return (
    <Link href={`/subject/${subject}`} passHref>
      <a className="flex flex-col justify-center items-center m-2 p-2 border border-black rounded-lg w-64 h-36 bg-primary bg-opacity-10 no-underline text-inherit">
        <h2>{name}</h2>
        <p>{professor}</p>
      </a>
    </Link>
  );
};

export default Course;