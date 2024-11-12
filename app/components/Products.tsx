import {ProductItem} from './ProductItem';
import {Card} from './Card';

export function Products(props: {items: { id: string; name: string; price: string; category: string }[]}) {
  let productDisplay: React.ReactNode = <p className="text-white text-center text-xl mt-8">No products</p>;

  if (props.items.length > 0) {
    productDisplay = props.items.map((product) => (
      <ProductItem key={product.id} item={product} />
    ));
  }

  return (
    <Card className="p-4 bg-gray-800 my-8 mx-auto w-128">
      {productDisplay}
    </Card>
  );
}

// export default Products;