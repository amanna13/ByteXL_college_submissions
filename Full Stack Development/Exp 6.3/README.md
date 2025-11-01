# Account Transfer System (exp6.3)

This demo shows how to implement a simple account transfer API using Node.js, Express and MongoDB without using DB transactions. It uses conditional updates and a best-effort rollback to keep state consistent.

Files
- `server.js` - Express server with `/accounts`, `/transfer` endpoints
- `models/Account.js` - Mongoose model
- `seed.js` - creates sample accounts (Alice and Bob)
- `package.json` - scripts: `start`, `seed`

Prerequisites
- Node.js 16+ and MongoDB running locally or available via URI.

Quick setup

1. Change into project folder

```powershell
cd "C:\Users\iases\Documents\ByteXL_college_submissions-main\Full Stack Development\exp6.3"
```

2. Install dependencies

```powershell
# If PowerShell blocks the npm.ps1 wrapper, use npm.cmd instead:
npm.cmd install

# (You can also run the plain npm command if your shell allows it)
npm install
```

3. (Optional) Set `MONGO_URI` environment variable if not using localhost default.

4. Seed sample accounts (prints IDs to use in tests)

```powershell
# If PowerShell blocks npm scripts, call the npm executable directly
npm.cmd run seed

# Or run the script file directly with node
# node seed.js
```

5. Start server

```powershell
# Recommended (works regardless of PowerShell npm wrapper):
npm.cmd start

# Or run directly with node:
node server.js
```

API

- GET /accounts
  - List accounts and balances

- POST /accounts
  - Create account
  - Body: { "name": "Charlie", "balance": 100 }

- POST /transfer
  - Transfer money from one account to another
  - Body: { "fromId": "<senderId>", "toId": "<receiverId>", "amount": 100 }

Transfer logic (no DB transactions)

1. The server first attempts an atomic decrement on the sender using a conditional update: it only decrements if the sender's balance is >= amount. This prevents overdrafts even under concurrent attempts.
2. If the decrement succeeds, it then increments the receiver. If the receiver does not exist, the server attempts a best-effort rollback by incrementing the sender back.
3. The endpoint returns meaningful errors for: missing accounts, insufficient balance, or internal failures.

Example tests (PowerShell)

1) Seed and start server as above. Note the printed IDs for Alice and Bob.

2) Successful transfer (replace IDs):

```powershell
$from = '<aliceId>'
$to = '<bobId>'
$body = @{ fromId = $from; toId = $to; amount = 200 } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri http://127.0.0.1:3003/transfer -Body $body -ContentType 'application/json'
```

3) Failed transfer due to insufficient funds:

```powershell
# Attempt to withdraw more than balance
$body = @{ fromId = $from; toId = $to; amount = 100000 } | ConvertTo-Json
Invoke-RestMethod -Method Post -Uri http://127.0.0.1:3003/transfer -Body $body -ContentType 'application/json'
```

Notes
- This demo intentionally avoids DB transactions to show how careful application logic and conditional updates can help maintain consistency. For production systems, prefer database transactions or other strong consistency mechanisms when supported.
