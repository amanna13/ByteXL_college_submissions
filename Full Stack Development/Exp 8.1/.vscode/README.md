VS Code helper tasks and launch configs for Exp 8.1

How to use

1. Open this folder in VS Code:

   File → Open Folder → select `Full Stack Development/Exp 8.1`.

2. Install dependencies (one-time):

   - Run the task: Terminal → Run Task... → `npm: install`

3. Start the dev server:

   - Run the task: Terminal → Run Task... → `npm: dev`
   - Or start the launch config which will also start the dev server and open Chrome:
     Run → Start Debugging (F5) → choose `Open in Browser (Starts dev server)`

Notes

- The `npm: dev` task runs Vite in the integrated terminal and is marked as a background task. Stopping the terminal (Ctrl+C) will stop the server.
- If you prefer not to auto-open Chrome, run `npm: dev` and open the printed URL in your browser.
- Recommended extensions are in `.vscode/extensions.json`.
