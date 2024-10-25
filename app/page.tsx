import React, { useEffect, useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import DueSubmissionAlert from '../DueSubmissionAlert';
import RecentCourses from '../RecentCourses';
import { useRouter } from 'next/router';

const MainPage: React.FC = () => {
  const [id, setCourseId] = useState('');
  const [subject_id, setSubjectId] = useState('');
  const router = useRouter();

  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (localStorage.getItem('accessToken') == null) {
      router.push('/login');
    }
    const fetchCourses = async () => {
      const response = await fetch('https://mpp.romail.app/api/v1/subject/student/1', {
        mode: 'cors',
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('accessToken')}`
        },
      });
      const data = await response.json();
      const initialCourses = data.map(item => ({
        name: item.name,
        professor: item.description
      }));
      setCourses(initialCourses);
      setLoading(false);
    };

    fetchCourses().catch(error => console.error('Failed to fetch courses:', error));
  }, []);

  const [displayedCourses, setDisplayedCourses] = useState(courses);
  useEffect(() => {
    setDisplayedCourses(courses);
  }, [courses]);

  const isDesktopOrLaptop = useMediaQuery({
    query: '(min-device-width: 1224px)'
  });
  const isTabletOrMobile = useMediaQuery({
    query: '(max-device-width: 1224px)'
  });

  return (
    <div className="flex flex-col items-center">
      <div className={isDesktopOrLaptop ? "text-base" : "text-lg"}>
        <h2 className="text-2xl font-bold mb-4">Timeline</h2>
        {courses.map((course, index) => (
          <DueSubmissionAlert key={index} subject={course.name} text={`${course.professor} is due.`} />
        ))}
        <RecentCourses courses={courses} />
      </div>
    </div>
  );
};

export default MainPage;