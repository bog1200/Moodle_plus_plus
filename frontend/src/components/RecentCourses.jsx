import Course from './Course.jsx';

function RecentCourses({ courses }) { // Accept courses as a prop
    return (
        <div>
            <h3>Recent Courses</h3>
            <div className="d-flex">
                {courses.map((course, index) => (
                    <Course key={index} name={course.name} professor={course.professor} />
                ))}
            </div>
        </div>
    );
}

export default RecentCourses;