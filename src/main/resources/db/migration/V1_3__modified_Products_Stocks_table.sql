ALTER TABLE PRODUCTS MODIFY productName varchar(50);
ALTER TABLE STOCKS DROP FOREIGN KEY stocks_ibfk_1;
ALTER TABLE STOCKS DROP productId;
