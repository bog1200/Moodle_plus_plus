import React from 'react';

interface ProductItemProps {
  item: {
    name: string;
    price: string;
    category: string;
  };
}

const ProductItem: React.FC<ProductItemProps> = ({ item }) => {
  const { name, price, category } = item;

  return (
    <div className="flex justify-evenly items-center p-2 my-4 rounded-lg bg-white">
      <div>{name}</div>
      <div>{price}</div>
      <div>{category}</div>
    </div>
  );
};

export default ProductItem;