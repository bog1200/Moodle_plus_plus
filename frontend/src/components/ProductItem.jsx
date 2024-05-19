import "./ProductItem.css";

function ProductItem(props) {
    // eslint-disable-next-line react/prop-types
    const name = props.item.name;
    // eslint-disable-next-line react/prop-types
    const price = props.item.price;
    // eslint-disable-next-line react/prop-types
    const category = props.item.category;

    return (
        <div className="product-item">
            <div>{name}</div>
            <div>{price}</div>
            <div>{category}</div>
        </div>
    );
}

export default ProductItem;