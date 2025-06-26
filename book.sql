-- Drop and recreate database
DROP DATABASE IF EXISTS jwtexhexa;
CREATE DATABASE jwtexhexa;
USE jwtexhexa;

-- ==========================
-- USER TABLE (JWT Auth Users)
-- ==========================
CREATE TABLE userdata (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20),
    email VARCHAR(50) UNIQUE,
    password VARCHAR(100),
    roles VARCHAR(40)
);

-- Sample user for testing (email: admin@example.com, password: admin123)
INSERT INTO userdata (name, email, password, roles)
VALUES (
    'admin',
    'admin@example.com',
    '$2a$10$1b2wfv88O6UjNqbrMY5FxeRZPq3tP3nsC3uAALHXJ57kArzlmjL3q',  -- password: admin123
    'ROLE_ADMIN'
);

-- ==========================
-- BOOK TABLE
-- ==========================
CREATE TABLE book (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publication_year INT CHECK (publication_year >= 1500)
);

-- Sample books
INSERT INTO book (isbn, title, author, publication_year) VALUES
('9780134685991', 'Effective Java', 'Joshua Bloch', 2018),
('9781617294945', 'Spring in Action', 'Craig Walls', 2021);
