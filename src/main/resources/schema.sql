-- Drop order_line_items first due to foreign key constraint
--DROP TABLE IF EXISTS order_line_items;

-- Drop orders table after
--DROP TABLE IF EXISTS orders;

-- Create table for orders
create table if not exists orders
(
    id serial primary key
);

-- Create table for order_line_items with a foreign key to orders
create table if not exists order_line_items
(
    id serial primary key,
    product int not null default 0,
    quantity int not null default 0,
    orders int references orders (id)  -- Adding foreign key to orders table
);