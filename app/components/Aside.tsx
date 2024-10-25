import Image from 'next/image';
import Link from 'next/link';
import { useRouter } from 'next/router';
import mortarboardFill from 'bootstrap-icons/icons/mortarboard-fill.svg';
import calendarCheck from 'bootstrap-icons/icons/calendar-check.svg';
import calendarEventFill from 'bootstrap-icons/icons/calendar-event-fill.svg';
import personFill from 'bootstrap-icons/icons/person-fill.svg';
import logoutFill from 'bootstrap-icons/icons/box-arrow-left.svg';
import logo from '../public/static/photos/logo.jpg';

const Aside = () => {
  const router = useRouter();

  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    router.push('/');
  };

  return (
    <aside className="flex flex-col items-center py-3 border bg-gray-100 shadow-sm rounded-lg">
      <div className="mb-4">
        <Image src={logo} alt="Logo" width={50} height={50} className="rounded-full" />
      </div>
      <Link href="/dashboard">
        <a className="mb-4">
          <Image src={mortarboardFill} alt="mortarboard" width={30} height={30} />
        </a>
      </Link>
      <Link href="/courses">
        <a className="mb-4">
          <Image src={calendarCheck} alt="calendar check" width={30} height={30} />
        </a>
      </Link>
      <Link href="/schedule">
        <a className="mb-4">
          <Image src={calendarEventFill} alt="calendar event" width={30} height={30} />
        </a>
      </Link>
      <Link href="/profile">
        <a className="mb-4">
          <Image src={personFill} alt="profile" width={30} height={30} />
        </a>
      </Link>
      <button onClick={handleLogout} className="mb-4">
        <Image src={logoutFill} alt="logout" width={30} height={30} />
      </button>
    </aside>
  );
};

export default Aside;