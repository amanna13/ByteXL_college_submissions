import React, { useState } from 'react';

function LoginForm() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [submitted, setSubmitted] = useState(false);

  function handleSubmit(e) {
    e.preventDefault();
    setSubmitted(true);
    if (!username.trim() || !password) {
      setError('Both username and password are required.');
      return;
    }
    setError('');
    // Log values to console as requested
    console.log('Login submit — username:', username);
    console.log('Login submit — password:', password);
    // For demo: show success message briefly
    alert('Submitted! Check console for values.');
  }

  return (
    <div className="page">
      <div className="card">
        <h1 className="title">Login</h1>
        <form onSubmit={handleSubmit} className="form">
          <label className="label">
            Username
            <input
              type="text"
              className="input"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              placeholder="Enter username"
            />
          </label>

          <label className="label">
            Password
            <input
              type="password"
              className="input"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter password"
            />
          </label>

          <button type="submit" className="btn">Submit</button>

          {submitted && error && (
            <div className="error" role="alert">{error}</div>
          )}

          <div className="hint">
            <strong>State preview:</strong>
            <div>username: {username || <em>(empty)</em>}</div>
            <div>password: {password ? '●'.repeat(password.length) : <em>(empty)</em>}</div>
          </div>
        </form>
      </div>
    </div>
  );
}

export default LoginForm;
