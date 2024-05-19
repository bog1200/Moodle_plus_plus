import ProductItem from "./ProductItem";
import "./Products.css";
import Card from "./Card";

export default function Products(props) {
    let productDisplay = <p className="empty">No products</p>;
    // eslint-disable-next-line react/prop-types
    if (props.items.length > 0) {
        productDisplay =
            // eslint-disable-next-line react/prop-types
            props.items.map((product) => <ProductItem
                key={product.id}
                item={product} />);
    } return (
        <Card className="products">
            {productDisplay}
        </Card>);
}