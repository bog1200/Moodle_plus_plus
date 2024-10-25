import React from 'react';

interface CourseAsListProps {
  name: string;
  professor: string;
}

const CourseAsList: React.FC<CourseAsListProps> = ({ name, professor }) => {
  return (
    <div className="flex flex-col justify-center items-center m-2 p-2 border border-black rounded-lg w-88 h-12 text-xs bg-primary bg-opacity-10">
      <h4 className="m-0">{name}</h4>
      <p className="m-0">{professor}</p>
    </div>
  );
};

export default CourseAsList;