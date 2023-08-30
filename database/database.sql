-- Table for product categories
CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

-- Table for products (clothing and shoes)
CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category_id INT REFERENCES category(id),
    size VARCHAR(20),
    gender VARCHAR(20),
    age VARCHAR(20),
    image_url VARCHAR(100),
    capacity int 
);

-- Table for buyers (users)
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);


-- Table for orders
CREATE TABLE "order" (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES "user"(id),
    order_date DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL
);



-- Table to manage shopping carts
CREATE TABLE cart (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES "user"(id),
    creation_date TIMESTAMP NOT NULL,
    quantity int
);

