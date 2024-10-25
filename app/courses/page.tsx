import React, { useEffect, useState } from 'react';
import searchIcon from 'bootstrap-icons/icons/search.svg';
import Course from '../Course';
import CourseAsList from '../CourseAsList';

const CoursesPage: React.FC = () => {
  const [selectedOption, setSelectedOption] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(true);
  const [courses, setCourses] = useState([]);
  const [displayedCourses, setDisplayedCourses] = useState([]);
  const coursesPerPage = 9;

  useEffect(() => {
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
        professor: item.description,
        subject: item.id
      }));
      setCourses(initialCourses);
      setLoading(false);
    };

    fetchCourses().catch(error => console.error('Failed to fetch courses:', error));
  }, []);

  useEffect(() => {
    setDisplayedCourses(courses);
  }, [courses]);

  const handleSelectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedOption(parseInt(event.target.value));
  };

  const handleNextPage = () => {
    setCurrentPage(currentPage + 1);
  };

  const handlePreviousPage = () => {
    setCurrentPage(currentPage - 1);
  };

  const handleSearch = () => {
    const filteredCourses = courses.filter(course => course.name.toLowerCase().includes(searchTerm.toLowerCase()));
    setCurrentPage(1);
    setDisplayedCourses(filteredCourses);
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  const startIndex = (currentPage - 1) * coursesPerPage;
  const selectedCourses = displayedCourses.slice(startIndex, startIndex + coursesPerPage);

  return (
    <div className="flex flex-col items-center">
      <h2>Courses</h2>
      <div className="flex items-center mb-3">
        <p className="m-3 w-25 rounded-3">Display as: </p>
        <select className="form-select me-2" value={selectedOption} onChange={handleSelectChange}>
          <option value="0">Card</option>
          <option value="1">List</option>
        </select>
        <input
          type="search"
          className="form-control me-2"
          placeholder="Search..."
          value={searchTerm}
          onChange={e => setSearchTerm(e.target.value)}
        />
        <button onClick={handleSearch}>
          <img src={searchIcon} alt="Search" width="30" height="30" />
        </button>
      </div>

      <div className="flex flex-wrap justify-center mt-5 w-128">
        {selectedOption === 0 ? (
          selectedCourses.map((course, index) => (
            <Course key={index} name={course.name} professor={course.professor} subject={course.subject} />
          ))
        ) : (
          <ul>
            {selectedCourses.map((course, index) => (
              <li key={index}>
                <CourseAsList name={course.name} professor={course.professor} subject={course.subject} />
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="flex justify-between mt-5">
        <button onClick={handlePreviousPage} disabled={currentPage === 1}>Previous</button>
        <button onClick={handleNextPage} disabled={startIndex + coursesPerPage >= courses.length}>Next</button>
      </div>
    </div>
  );
};

export default CoursesPage;