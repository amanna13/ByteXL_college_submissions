import React from "react";
import ProductCard from "./ProductCard";

export default function App() {
  const containerStyle = {
    minHeight: "100vh",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    padding: "20px",
    backgroundColor: "#f0f0f0",
    gap: "20px"
  };

  const titleStyle = {
    fontSize: "32px",
    fontWeight: "bold",
    marginBottom: "20px"
  };

  const productsList = [
    {
      name: "Wireless Mouse",
      price: "25.99",
      status: "In Stock"
    },
    {
      name: "Keyboard",
      price: "45.5",
      status: "Out of Stock"
    },
    {
      name: "Monitor",
      price: "199.99",
      status: "In Stock"
    }
  ];

  return (
    <div style={containerStyle}>
      <h1 style={titleStyle}>Products List</h1>
      <div style={{
        display: "flex",
        justifyContent: "center",
        gap: "20px",
        flexWrap: "wrap"
      }}>
        {productsList.map((product, index) => (
          <ProductCard
            key={index}
            name={product.name}
            price={product.price}
            status={product.status}
          />
        ))}
      </div>
    </div>
  );
}
