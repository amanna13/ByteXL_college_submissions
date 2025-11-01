## Title
Build Role-Based Access Control with Admin, User, and Moderator Roles

## Objective
Learn how to implement role-based access control (RBAC) in a Node.js and Express application to restrict access to certain routes or actions based on user roles. This task helps you understand how to store and check user roles in JWT tokens, create flexible authorization logic, and secure sensitive parts of your backend API.

### Task Description
Create a Node.js and Express.js backend that supports user authentication with different roles, including Admin, User, and Moderator. Implement a login route that issues JWT tokens containing the user's role in the payload. Create middleware to verify the JWT and extract the user's role. Implement separate protected routes that can only be accessed by specific roles, for example, an Admin-only dashboard route, a Moderator management route, and a general User profile route. Ensure that requests with invalid tokens or insufficient roles are properly denied with clear error messages. Test your system by logging in with different roles and accessing each route to confirm that the role-based restrictions work as expected.