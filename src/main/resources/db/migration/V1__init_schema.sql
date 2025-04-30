-- === Table: categories ===
CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            parent_id INTEGER,
                            CONSTRAINT fk_parent_category FOREIGN KEY (parent_id) REFERENCES categories(id)
);

-- === Table: products ===
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          available BOOLEAN NOT NULL DEFAULT true,
                          category_id INTEGER NOT NULL,
                          category_path TEXT NOT NULL,
                          CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- === Insert category hierarchy ===
-- Electronics > Phones > Smartphones
INSERT INTO categories (id, name, parent_id) VALUES (1, 'Electronics', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (2, 'Phones', 1);
INSERT INTO categories (id, name, parent_id) VALUES (3, 'Smartphones', 2);

-- Electronics > Computers > Laptops
INSERT INTO categories (id, name, parent_id) VALUES (4, 'Computers', 1);
INSERT INTO categories (id, name, parent_id) VALUES (5, 'Laptops', 4);

-- Home > Kitchen > Appliances
INSERT INTO categories (id, name, parent_id) VALUES (6, 'Home', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (7, 'Kitchen', 6);
INSERT INTO categories (id, name, parent_id) VALUES (8, 'Appliances', 7);

-- Tools > Hand Tools > Hammers > Sledgehammer
INSERT INTO categories (id, name, parent_id) VALUES (9, 'Tools', NULL);
INSERT INTO categories (id, name, parent_id) VALUES (10, 'Hand Tools', 9);
INSERT INTO categories (id, name, parent_id) VALUES (11, 'Hammers', 10);
INSERT INTO categories (id, name, parent_id) VALUES (12, 'Sledgehammer', 11);

-- === Insert sample products ===

-- Smartphones
INSERT INTO products (name, description, price, available, category_id, category_path)
VALUES ('iPhone 14 Pro', 'Latest Apple smartphone with triple camera system', 1199.99, true, 3, 'Electronics > Phones > Smartphones');

-- Laptops
INSERT INTO products (name, description, price, available, category_id, category_path)
VALUES ('Dell XPS 13', '13-inch ultrabook with Intel i7 and 16GB RAM', 1299.00, true, 5, 'Electronics > Computers > Laptops');

-- Kitchen Appliance
INSERT INTO products (name, description, price, available, category_id, category_path)
VALUES ('KitchenAid Mixer', 'Stand mixer with 10 speed settings', 349.99, true, 8, 'Home > Kitchen > Appliances');

-- Hammer
INSERT INTO products (name, description, price, available, category_id, category_path)
VALUES ('Heavy Duty Sledgehammer', 'Ideal for demolition work and masonry', 89.50, true, 12, 'Tools > Hand Tools > Hammers > Sledgehammer');
