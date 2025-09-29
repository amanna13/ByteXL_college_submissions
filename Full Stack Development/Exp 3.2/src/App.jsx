import React, { useState } from "react";
import BookList from "./BookList";

export default function App() {
  const [books, setBooks] = useState([
    { title: "1984", author: "George Orwell" },
    { title: "The Great Gatsby", author: "F. Scott Fitzgerald" },
    { title: "To Kill a Mockingbird", author: "Harper Lee" }
  ]);

  const [searchTerm, setSearchTerm] = useState("");
  const [newBook, setNewBook] = useState({ title: "", author: "" });

  // Add a new book
  const handleAddBook = () => {
    if (newBook.title && newBook.author) {
      setBooks([...books, newBook]);
      setNewBook({ title: "", author: "" });
    }
  };

  // Remove a book
  const handleRemoveBook = (index) => {
    const updatedBooks = books.filter((_, i) => i !== index);
    setBooks(updatedBooks);
  };

  // Filter books based on search term
  const filteredBooks = books.filter(
    (book) =>
      book.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      book.author.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const containerStyle = {
    padding: "20px",
    maxWidth: "800px",
    margin: "0 auto"
  };

  const inputContainerStyle = {
    marginBottom: "20px"
  };

  const searchStyle = {
    width: "100%",
    padding: "8px",
    marginBottom: "15px",
    fontSize: "14px"
  };

  const addBookStyle = {
    display: "flex",
    gap: "10px"
  };

  const inputStyle = {
    flex: "1",
    padding: "8px",
    fontSize: "14px"
  };

  const buttonStyle = {
    padding: "8px 16px",
    backgroundColor: "#f0f0f0",
    border: "1px solid #ddd",
    borderRadius: "4px",
    cursor: "pointer"
  };

  return (
    <div style={containerStyle}>
      <h1>Library Management</h1>

      <div style={inputContainerStyle}>
        {/* Search */}
        <input
          type="text"
          placeholder="Search by title or author"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          style={searchStyle}
        />

        {/* Add New Book */}
        <div style={addBookStyle}>
          <input
            type="text"
            placeholder="New book title"
            value={newBook.title}
            onChange={(e) => setNewBook({ ...newBook, title: e.target.value })}
            style={inputStyle}
          />
          <input
            type="text"
            placeholder="New book author"
            value={newBook.author}
            onChange={(e) => setNewBook({ ...newBook, author: e.target.value })}
            style={inputStyle}
          />
          <button onClick={handleAddBook} style={buttonStyle}>
            Add Book
          </button>
        </div>
      </div>

      {/* Book List */}
      <BookList books={filteredBooks} handleRemoveBook={handleRemoveBook} />
    </div>
  );
}
