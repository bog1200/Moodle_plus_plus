import React from 'react';

export function ProductItem(props: {item: {name: string, price: string, category: string}}) {
  const { name, price, category } = props.item;

  return (
    <div className="flex justify-evenly items-center p-2 my-4 rounded-lg bg-white">
      <div>{name}</div>
      <div>{price}</div>
      <div>{category}</div>
    </div>
  );
}

// export default ProductItem;