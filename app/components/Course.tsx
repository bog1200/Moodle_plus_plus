// import Image from 'next/image';
import Link from 'next/link';

export function Course(props: {name: string, professor: string, subject: string}) {
  return (///               ${props.subject}
    <Link href={`/app/dashboard/subject`} className="flex flex-col justify-center items-center m-2 p-2 border border-foreground rounded-lg w-64 h-36 bg-primary bg-opacity-10 no-underline text-inherit">
        <h2>{props.name}</h2>
        <p>{props.professor}</p>
    </Link>
  );
} //passHref