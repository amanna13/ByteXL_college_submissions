const mongoose = require('mongoose');
const CatalogProduct = require('./models/CatalogProduct');
require('dotenv').config();

async function runCatalogDemo() {
  try {
    await mongoose.connect(process.env.MONGO_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true
    });
    console.log('Connected to MongoDB');

    // 1. Insert sample products with variants
    const sampleProducts = [
      {
        name: 'T-Shirt',
        price: 19.99,
        category: 'Apparel',
        variants: [
          { color: 'Red', size: 'M', stock: 10 },
          { color: 'Blue', size: 'L', stock: 5 },
          { color: 'Black', size: 'S', stock: 8 }
        ]
      },
      {
        name: 'Sneakers',
        price: 59.99,
        category: 'Footwear',
        variants: [
          { color: 'White', size: '42', stock: 7 },
          { color: 'Black', size: '41', stock: 3 },
          { color: 'Red', size: '40', stock: 2 }
        ]
      },
      {
        name: 'Backpack',
        price: 29.99,
        category: 'Accessories',
        variants: [
          { color: 'Green', size: 'Standard', stock: 12 },
          { color: 'Blue', size: 'Large', stock: 6 }
        ]
      },
      {
        name: 'Jeans',
        price: 39.99,
        category: 'Apparel',
        variants: [
          { color: 'Blue', size: '32', stock: 15 },
          { color: 'Black', size: '34', stock: 9 }
        ]
      },
      {
        name: 'Watch',
        price: 99.99,
        category: 'Accessories',
        variants: [
          { color: 'Silver', size: 'Standard', stock: 5 },
          { color: 'Gold', size: 'Standard', stock: 2 }
        ]
      },
      {
        name: 'Hat',
        price: 14.99,
        category: 'Apparel',
        variants: [
          { color: 'Black', size: 'M', stock: 7 },
          { color: 'White', size: 'L', stock: 4 }
        ]
      }
    ];
    await CatalogProduct.deleteMany({}); // Clean up before insert
    const inserted = await CatalogProduct.insertMany(sampleProducts);
    console.log('Inserted products:', inserted);

    // 2. Retrieve all products
    const allProducts = await CatalogProduct.find();
    console.log('All products:', allProducts);

    // 3. Filter products by category
    const apparelProducts = await CatalogProduct.find({ category: 'Apparel' });
    console.log('Apparel products:', apparelProducts);

    // 4. Project specific variant details (e.g., only color and stock)
    const variantProjection = await CatalogProduct.find({}, { 'variants.color': 1, 'variants.stock': 1, name: 1 });
    console.log('Products with variant color and stock:', variantProjection);

    await mongoose.disconnect();
    console.log('Disconnected from MongoDB');
  } catch (err) {
    console.error('Error:', err.message);
    process.exit(1);
  }
}

runCatalogDemo();
