import React from 'react';

interface CardProps {
  className?: string;
  children: React.ReactNode;
}

const Card: React.FC<CardProps> = ({ className = '', children }) => {
  const classes = `rounded-lg shadow-md ${className}`;
  return <div className={classes}>{children}</div>;
};

export default Card;