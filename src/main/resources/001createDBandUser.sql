CREATE DATABASE IF NOT EXISTS cryptocurrencywallet;
CREATE USER IF NOT EXISTS 'cryptocurrencywallet' IDENTIFIED BY 'cryptocurrencywallet';
GRANT ALL PRIVILEGES ON cryptocurrencywallet.* TO 'cryptocurrencywallet';