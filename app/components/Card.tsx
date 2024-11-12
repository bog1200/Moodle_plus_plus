import React from 'react';


export function Card(props: {className: string, children: React.ReactNode}) {
  const classes = `rounded-lg shadow-md ${props.className}`;
  return <div className={classes}>{props.children}</div>;
}

//export default Card;