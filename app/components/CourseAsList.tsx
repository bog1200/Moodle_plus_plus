import React from 'react';



export function CourseAsList(props: {name:string, professor:string}) {
  return (
    <div className="flex flex-col justify-center items-center m-2 p-2 border border-black rounded-lg w-88 h-12 text-xs bg-primary bg-opacity-10">
      <h4 className="m-0">{props.name}</h4>
      <p className="m-0">{props.professor}</p>
    </div>
  );
}
