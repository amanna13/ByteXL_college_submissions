# Express Middleware Demo - Logging & Bearer Token Auth

This project demonstrates two custom Express middleware functions:
1. Global logging middleware that logs all incoming requests
2. Bearer token authentication middleware for protected routes

## Project Structure

- `server.js` - Express server with middleware implementations
- `package.json` - Project dependencies (Express)

## Routes Documentation

### 1. Public Route
- **URL:** `GET http://localhost:3000/`
- **Authentication:** None required
- **Description:** Public endpoint accessible to all users
- **Example Request:**
```bash
curl -i http://localhost:3000/
```
- **Expected Response:**
```json
{
    "message": "Public route: no authentication required."
}
```

### 2. Protected Route
- **URL:** `GET http://localhost:3000/protected`
- **Authentication:** Bearer token required
- **Token:** `mysecrettoken`
- **Description:** Protected endpoint requiring valid bearer token
- **Header Required:** `Authorization: Bearer mysecrettoken`

#### Test Cases:

a) Without Token:
```bash
curl -i http://localhost:3000/protected
```
Expected Response (401):
```json
{
    "error": "Authorization header missing"
}
```

b) With Wrong Token:
```bash
curl -i -H "Authorization: Bearer wrongtoken" http://localhost:3000/protected
```
Expected Response (403):
```json
{
    "error": "Invalid token"
}
```

c) With Correct Token:
```bash
curl -i -H "Authorization: Bearer mysecrettoken" http://localhost:3000/protected
```
Expected Response (200):
```json
{
    "message": "Protected route: access granted with valid token."
}
```

## Running the Project

1. Install dependencies:
```bash
npm install
```

2. Start the server:
```bash
npm start
```
Server will start on http://localhost:3000

## PowerShell Alternative Commands

If you're using PowerShell and prefer `Invoke-RestMethod`:

1. Public Route:
```powershell
Invoke-RestMethod -Uri http://localhost:3000 -Method Get
```

2. Protected Route with Token:
```powershell
$headers = @{
    Authorization = "Bearer mysecrettoken"
}
Invoke-RestMethod -Uri http://localhost:3000/protected -Method Get -Headers $headers
```

## Logging

The logging middleware logs every request to the console in this format:
```
[TIMESTAMP] METHOD URL
```
Example:
```
[2025-10-31T12:34:56.789Z] GET /protected
```

## Status Codes

- 200: Success
- 401: Missing Authorization header
- 403: Invalid token
- 404: Route not found

## Testing All Routes

Open a new terminal and run these commands to test all routes:

```bash
# 1. Test public route
curl -i http://localhost:3000/

# 2. Test protected route without token
curl -i http://localhost:3000/protected

# 3. Test protected route with wrong token
curl -i -H "Authorization: Bearer wrongtoken" http://localhost:3000/protected

# 4. Test protected route with correct token
curl -i -H "Authorization: Bearer mysecrettoken" http://localhost:3000/protected
```

## Command Prompt (cmd.exe) Usage

If you're using Windows Command Prompt:
```cmd
REM Test protected route with token
curl.exe -i -H "Authorization: Bearer mysecrettoken" http://localhost:3000/protected
```

## Troubleshooting

1. If npm commands fail in PowerShell:
   - Use Command Prompt (cmd.exe) instead, or
   - Set PowerShell execution policy:
     ```powershell
     Set-ExecutionPolicy -Scope CurrentUser -ExecutionPolicy RemoteSigned -Force
     ```

2. If connection is refused:
   - Verify the server is running (`npm start`)
   - Check console for any error messages
   - Ensure port 3000 is not in use

3. If curl is not recognized:
   - Use full path: `C:\Windows\System32\curl.exe`
   - Or use PowerShell's `Invoke-RestMethod`
