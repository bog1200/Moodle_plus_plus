import { useRef } from "react";

export default function AddProduct(props) {
  const inputVal = useRef("");

  const clickHandler = () => {
    const newProduct = {
      id: Math.random(),
      name: inputVal.current.value,
      category: "utility",
    };

    fetch('http://localhost:5000/api/products', { // replace with your actual backend API endpoint
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(newProduct),
    })
    .then(response => response.json())
    .then(data => {
      props.onAddProduct(data);
    });
  };

  return (
    <div>
      <span>Enter value</span>
      <input type="text" ref={inputVal}></input>
      <button onClick={clickHandler}>Change value</button>
    </div>
  );
}
