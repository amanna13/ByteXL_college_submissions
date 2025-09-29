import React from "react";

export default function ProductCard({ name, price, status }) {
  const cardStyle = {
    padding: "20px",
    margin: "10px",
    border: "1px solid #ddd",
    borderRadius: "8px",
    backgroundColor: "white",
    width: "200px",
    textAlign: "center"
  };

  const nameStyle = {
    fontSize: "20px",
    fontWeight: "bold",
    marginBottom: "10px"
  };

  const priceStyle = {
    fontSize: "16px",
    color: "#333",
    margin: "10px 0"
  };

  const statusStyle = {
    fontSize: "14px",
    color: status === "In Stock" ? "green" : "red"
  };

  return (
    <div style={cardStyle}>
      <h2 style={nameStyle}>{name}</h2>
      <p style={priceStyle}>Price: ${price}</p>
      <p style={statusStyle}>Status: {status}</p>
    </div>
  );
}
