---

# Getting Started with MongoDB Atlas (Beginner Guide)

If you are new to MongoDB, here is how you can set up a free cloud database and connect it to these experiments:

## 1. Create a MongoDB Atlas Account

- Go to [https://www.mongodb.com/cloud/atlas/register](https://www.mongodb.com/cloud/atlas/register)
- Sign up for a free account

## 2. Create a Cluster

- Click "Build a Database"
- Choose the free tier (Shared, M0)
- Select your cloud provider and region (any is fine)
- Click "Create"

## 3. Add a Database User

- In the left menu, click "Database Access"
- Click "Add New Database User"
- Set a username and password (remember these!)
- Give "Read and Write to any database" permissions
- Click "Add User"

## 4. Allow Network Access

- In the left menu, click "Network Access"
- Click "Add IP Address"
- Click "Allow Access from Anywhere" (or add your own IP)
- Click "Confirm"

## 5. Get Your Connection String

- In the left menu, click "Clusters"
- Click "Connect" > "Connect your application"
- Copy the connection string (it looks like: `mongodb+srv://<username>:<password>@...`)

## 6. Add Your Connection String to `.env`

- Open the `.env` file in your project
- Paste your connection string as the value for `MONGO_URI`
- Example:
   ```env
   MONGO_URI=mongodb+srv://yourusername:yourpassword@cluster0.mongodb.net/?retryWrites=true&w=majority
   ```

## 7. Run the Experiments

- Now you can run any of the scripts as shown above!

## 8. View Your Data in Atlas

- Go to "Clusters" > "Collections"
- You will see your databases and collections
- Click on a collection to view, edit, or delete documents

---
# Easy Guide: MongoDB Experiments 5.1, 5.2, 5.3

This guide explains how to run and understand all three experiments in simple steps. No coding experience is needed!

---

## Experiment 5.1: Product CRUD

**What it does:**
- Adds a product to the database
- Shows all products
- Updates a product
- Deletes a product

**How to run:**
1. Open your terminal
2. Type:
   ```bash
   node crudScript.js
   ```
3. You will see messages for each step (Added, Updated, Deleted)

---

## Experiment 5.2: Student Management System

**What it does:**
- Adds 4 students to the database
- Updates 3 students
- Deletes 2 students
- Shows all results in the terminal

**How to run:**
1. Open your terminal
2. Type:
   ```bash
   node studentCrudScript.js
   ```
3. You will see messages for each step (Added, Updated, Deleted)

---

## Experiment 5.3: E-commerce Catalog with Variants

**What it does:**
- Adds sample products with different variants (color, size, stock)
- Shows all products
- Filters products by category
- Shows only color and stock for product variants

**How to run:**
1. Open your terminal
2. Type:
   ```bash
   node catalogDemo.js
   ```
3. You will see messages for each step (Inserted products, All products, Apparel products, Products with variant color and stock)

---

## What you need
- Node.js installed
- MongoDB connection (local or Atlas)
- The `.env` file with your MongoDB URI

## No coding needed
Just run the commands above. All steps are automatic and results are shown in your terminal.

## Troubleshooting
- If you see a connection error, check that MongoDB is running and your `.env` file is correct.
- If you want to see the data, just look at the messages printed in the terminal after running each script.

---

**You are ready to run all experiments!**
