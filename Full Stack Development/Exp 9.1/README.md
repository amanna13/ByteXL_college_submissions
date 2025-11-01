## Title
Dockerize a React Application with Multi-Stage Build

## Objective
Learn how to create a production-ready Docker image for a React application using a multi-stage Docker build. This helps you reduce image size, separate build dependencies from runtime, and prepare your app for deployment.

## Task Description
Build a simple React application (for example, created using Create React App). Write a multi-stage Dockerfile:

Use a Node.js image as the first stage to install dependencies and build the React app.
Use an Nginx image as the second stage to serve the compiled static files.
Add a .dockerignore file to exclude unnecessary files. Build the Docker image locally and run it to test if the React app is correctly served on localhost. Verify that the final image size is smaller than including all dev dependencies.