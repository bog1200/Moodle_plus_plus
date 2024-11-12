import {Course} from './Course';

export function RecentCourses(props: {courses: { name: string; professor: string; subject: string;}[]}) {
  return (
    <div>
      <h3>Recent Courses</h3>
      <div className="flex">
        {props.courses.map((course, index) => (
          <Course key={index} name={course.name} professor={course.professor} subject={course.subject}/>
        ))}
      </div>
    </div>
  );
}

// export default RecentCourses;