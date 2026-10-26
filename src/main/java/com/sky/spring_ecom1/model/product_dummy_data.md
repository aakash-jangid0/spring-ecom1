# Dummy product data

Run this SQL against the PostgreSQL `ecom` database after the `products` table has been created. The `id` column is omitted because it is generated automatically.

```sql
INSERT INTO products
    (name, description, brand, price, category, release_date, product_available, stock_quantity)
VALUES
    ('Wireless Mouse', 'Ergonomic wireless mouse with adjustable DPI.', 'Logitech', 24.99, 'Electronics', '2024-03-15', TRUE, 85),
    ('Mechanical Keyboard', 'Tenkeyless mechanical keyboard with blue switches.', 'Keychron', 79.50, 'Electronics', '2023-11-02', TRUE, 32),
    ('Stainless Steel Water Bottle', 'Insulated 750 ml bottle that keeps drinks cold.', 'Hydro Flask', 34.00, 'Outdoor', '2024-05-20', TRUE, 48),
    ('Everyday Backpack', 'Lightweight 20 L backpack with padded laptop sleeve.', 'North Face', 59.95, 'Accessories', '2022-08-10', TRUE, 17),
    ('Ceramic Coffee Mug', 'Dishwasher-safe 350 ml ceramic mug.', 'Fellow', 18.00, 'Kitchen', '2023-06-01', TRUE, 120),
    ('USB-C Hub', 'Seven-port hub with HDMI, USB-A, and SD card reader.', 'Anker', 42.99, 'Electronics', '2024-01-12', FALSE, 0),
    ('Running Shoes', 'Breathable daily trainers with cushioned sole.', 'Asics', 89.00, 'Footwear', '2023-09-18', TRUE, 26),
    ('Desk Lamp', 'Dimmable LED lamp with adjustable arm.', 'Philips', 31.75, 'Home Office', '2022-12-05', TRUE, 14),
    ('Notebook Set', 'Three ruled A5 notebooks with recycled paper.', 'Moleskine', 15.25, 'Stationery', '2024-02-28', TRUE, 63),
    ('Bluetooth Speaker', 'Portable waterproof speaker with 12-hour battery.', 'JBL', 49.99, 'Electronics', '2021-07-14', FALSE, 0);
```

`release_date` maps to the default PostgreSQL column name for `releaseDate`. If you change the entity naming strategy, adjust the SQL column names to match the actual table schema.
