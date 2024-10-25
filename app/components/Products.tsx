import ProductItem from './ProductItem';
import Card from './Card';

interface ProductsProps {
  items: { id: string; name: string; price: string; category: string }[];
}

const Products: React.FC<ProductsProps> = ({ items }) => {
  let productDisplay = <p className="text-white text-center text-xl mt-8">No products</p>;

  if (items.length > 0) {
    productDisplay = items.map((product) => (
      <ProductItem key={product.id} item={product} />
    ));
  }

  return (
    <Card className="p-4 bg-gray-800 my-8 mx-auto w-128">
      {productDisplay}
    </Card>
  );
};

export default Products;