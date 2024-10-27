// import Image from 'next/image';
import Link from 'next/link';

export function Course(props: {name: string, professor: string, subject: string}) {
  return (
    <Link href={`/subject/${props.subject}`} passHref>
      <a className="flex flex-col justify-center items-center m-2 p-2 border border-black rounded-lg w-64 h-36 bg-primary bg-opacity-10 no-underline text-inherit">
        <h2>{props.name}</h2>
        <p>{props.professor}</p>
      </a>
    </Link>
  );
}