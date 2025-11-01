# JWT Banking API (exp6.2)

This is a small Express.js exercise demonstrating JWT-based authentication for protecting banking endpoints.

Files:
- `server.js` - Express server with `/login`, `/balance`, `/deposit`, `/withdraw`
- `package.json` - project manifest

Prerequisites

- Node.js (14+ recommended) installed and available on your PATH.
- A terminal; the examples below use PowerShell (Windows) since this repository is on a Windows machine.

Full, step-by-step run & test instructions (PowerShell)

1) Open PowerShell and change into the project folder:

```powershell
cd "C:\Users\iases\Documents\ByteXL_college_submissions-main\Full Stack Development\exp6.2"
```

2) Install dependencies:

```powershell
npm install
```

3) Start the server (default port 3000):

```powershell
npm start
# or for development with auto-reload (requires nodemon):
npm run dev
```

The server prints a message like:

```
Banking API with JWT running on http://localhost:3000
```

4) Obtain a JWT by logging in (the app uses a hardcoded credential for this exercise):

Request body: { "username": "user1", "password": "password123" }

PowerShell example (captures token into $token):

```powershell
#$login = Invoke-RestMethod -Method Post -Uri http://127.0.0.1:3000/login -Body (@{ username='user1'; password='password123' } | ConvertTo-Json) -ContentType 'application/json'
$login = Invoke-RestMethod -Method Post -Uri http://127.0.0.1:3000/login -Body (@{ username='user1'; password='password123' } | ConvertTo-Json) -ContentType 'application/json'
$token = $login.token
Write-Output "Token: $token"
```

5) Call protected endpoints by sending the token as a Bearer token in the Authorization header.

GET balance:

```powershell
Invoke-RestMethod -Uri http://127.0.0.1:3000/balance -Headers @{ Authorization = "Bearer $token" }
```

Deposit (adds money):

```powershell
Invoke-RestMethod -Method Post -Uri http://127.0.0.1:3000/deposit -Body (@{ amount = 200 } | ConvertTo-Json) -ContentType 'application/json' -Headers @{ Authorization = "Bearer $token" }
```

Withdraw (subtracts money):

```powershell
Invoke-RestMethod -Method Post -Uri http://127.0.0.1:3000/withdraw -Body (@{ amount = 150 } | ConvertTo-Json) -ContentType 'application/json' -Headers @{ Authorization = "Bearer $token" }
```

Example using curl (Linux/macOS or curl on Windows):

```bash
curl -X POST -H "Content-Type: application/json" -d '{"username":"user1","password":"password123"}' http://127.0.0.1:3000/login
curl -H "Authorization: Bearer <token>" http://127.0.0.1:3000/balance
```

Environment variables and optional configuration

- `PORT` - change the server port if needed. Example (PowerShell):

```powershell
$env:PORT = '4000'; npm start
```

- `JWT_SECRET` - currently the demo uses a hardcoded secret inside `server.js`. To make it configurable, you can set an environment variable and then modify `server.js` to read from `process.env.JWT_SECRET`.

Troubleshooting

- "Unable to connect" when calling endpoints: ensure the server is running and that the correct host/port are used (127.0.0.1:3000).
- If `npm` is blocked by PowerShell execution policy on scripts (e.g., `npm.ps1 cannot be loaded`), run npm via `npm.cmd` or set execution policy appropriately. Example quick alternative:

```powershell
npm.cmd install
npm.cmd start
```

- If you get `Invalid or expired token`, re-run the login step to get a fresh token (tokens expire after 1 hour in this demo).

If you get an error like "Error: listen EADDRINUSE: address already in use :::3000" when starting the server, another process is already listening on port 3000. Use the following PowerShell commands in the VS Code terminal to find and stop that process:

```powershell
# Find listeners on port 3000 (shows PID)
netstat -ano | Select-String ":3000"

# Or a structured view
Get-NetTCPConnection -LocalPort 3000 | Format-List

# Inspect the process (replace <PID> with the number you found)
Get-Process -Id <PID> | Format-List Id,ProcessName,StartTime

# Stop the process (graceful)
Stop-Process -Id <PID>

# Or force kill
Stop-Process -Id <PID> -Force

# Alternative Windows taskkill
taskkill /PID <PID> /F

# Verify port is free
Test-NetConnection -ComputerName 127.0.0.1 -Port 3000
```

If you prefer not to stop the other process, run the demo on another port:

```powershell
$env:PORT = '4000'; node server.js
```

Design notes and limitations

- Credentials and JWT secret are hardcoded for exercise purposes — DO NOT use this in production.
- The account is stored in-memory. Restarting the server resets the balance.
- The demo focuses on illustrating JWT generation, verification middleware, and protected routes.

Next steps (optional enhancements)

- Move secrets to environment variables and load with `dotenv`.
- Replace in-memory state with a simple JSON file or a real database (SQLite, MongoDB).
- Add automated tests (Jest or Mocha) to cover login and protected endpoints.

If you'd like, I can implement any of the optional enhancements or add an automated test script next.
