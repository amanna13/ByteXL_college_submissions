import React from "react";

export default function BookList({ books, handleRemoveBook }) {
  const listStyle = {
    listStyleType: "none",
    padding: 0,
    margin: 0
  };

  const bookItemStyle = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "10px 0",
    borderBottom: "1px solid #eee"
  };

  const bookInfoStyle = {
    display: "flex",
    gap: "5px"
  };

  const removeButtonStyle = {
    padding: "4px 12px",
    backgroundColor: "#f0f0f0",
    border: "1px solid #ddd",
    borderRadius: "4px",
    cursor: "pointer"
  };

  return (
    <ul style={listStyle}>
      {books.length === 0 && <p>No books found.</p>}
      {books.map((book, index) => (
        <li key={index} style={bookItemStyle}>
          <div style={bookInfoStyle}>
            <strong>{book.title}</strong>
            <span>by</span>
            <span>{book.author}</span>
          </div>
          <button
            onClick={() => handleRemoveBook(index)}
            style={removeButtonStyle}
          >
            Remove
          </button>
        </li>
      ))}
    </ul>
  );
}
