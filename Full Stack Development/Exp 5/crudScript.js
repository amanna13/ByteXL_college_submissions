const mongoose = require('mongoose');
const Product = require('./models/Product');
require('dotenv').config();

async function runCRUD() {
  try {
    await mongoose.connect(process.env.MONGO_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true
    });
    console.log('Connected to MongoDB');

    // 1. Add multiple products
    const productData = [
      { name: 'Script Product', price: 49.99, category: 'Script Category' },
      { name: 'Laptop', price: 999.99, category: 'Electronics' },
      { name: 'Coffee Mug', price: 9.99, category: 'Kitchen' },
      { name: 'Notebook', price: 2.99, category: 'Stationery' },
      { name: 'Headphones', price: 79.99, category: 'Electronics' }
    ];
    await Product.deleteMany({}); // Clean up before insert
    const newProducts = await Product.insertMany(productData);
    console.log('Added:', newProducts);

    // 2. Retrieve all products
    const products = await Product.find();
    console.log('All Products:', products);

    // 3. Update the first 3 products by ID
    for (let i = 0; i < 3 && i < products.length; i++) {
      const updated = await Product.findByIdAndUpdate(
        products[i]._id,
        { name: products[i].name + ' Updated', price: products[i].price + 10 },
        { new: true, runValidators: true }
      );
      console.log('Updated:', updated);
    }

    // 4. Delete the first 2 products by ID
    for (let i = 0; i < 2 && i < products.length; i++) {
      const deleted = await Product.findByIdAndDelete(products[i]._id);
      console.log('Deleted:', deleted);
    }

    await mongoose.disconnect();
    console.log('Disconnected from MongoDB');
  } catch (err) {
    console.error('Error:', err.message);
    process.exit(1);
  }
}

runCRUD();
