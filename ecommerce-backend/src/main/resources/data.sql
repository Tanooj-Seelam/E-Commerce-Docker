-- FILL IN YOUR DATA HERE FOR THE TABLES

INSERT INTO users (id, username, email, password) VALUES
(1, 'alice123', 'alice@example.com', 'password123'),
(2, 'bob456', 'bob@example.com', 'securepass456');

INSERT INTO products (id, name, description, price, inventory_count) VALUES
(1, 'Wireless Mouse', 'Ergonomic wireless mouse', 2500, 50),
(2, 'Mechanical Keyboard', 'RGB backlit keyboard', 5500, 30),
(3, 'Laptop Stand', 'Aluminum adjustable stand', 3200, 40);

INSERT INTO carts (id, user_id) VALUES
(1, 1),
(2, 2);

INSERT INTO cart_items (id, cart_id, product_id, quantity) VALUES
(1, 1, 1, 2), -- Alice: 2 Mice
(2, 1, 2, 1), -- Alice: 1 Keyboard
(3, 2, 3, 1); -- Bob: 1 Stand

INSERT INTO orders (id, total_amount, created_at, user_id) VALUES
(1, 10500, '2025-04-15 10:00:00', 1),
(2, 3200, '2025-04-16 14:30:00', 2);

INSERT INTO order_items (id, order_id, product_id, quantity, price_at_purchase) VALUES
(1, 1, 1, 2, 2500), -- 2 Mice at 2500 each
(2, 1, 2, 1, 5500), -- 1 Keyboard at 5500
(3, 2, 3, 1, 3200); -- 1 Stand at 3200

INSERT INTO canceled_orders (id, created_at, total_amount, user_id) VALUES
(2, '2025-04-17 09:00:00', 3200, 2); -- Bob canceled order #2

INSERT INTO canceled_order_items (id, canceled_order_id, product_id, quantity, price_at_purchase) VALUES
(1, 2, 3, 1, 3200); -- 1 Stand

-- Add a test user
INSERT INTO users (username, email, password, name) 
VALUES ('testuser', 'test@example.com', 'password', 'Test User')
ON DUPLICATE KEY UPDATE username=username;
