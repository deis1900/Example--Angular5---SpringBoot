INSERT INTO Product (id, type_of_clothes, material, size, color, date_Of_Last_Change, image) VALUES (1, 'Dress', 'Cotton', '42-48', 'Red', 9865654598, 'https://images.ua.prom.st/705057263_w800_h640_cid2327845_pid495526672-988a9c74.jpg'),
(2, 'Suit', 'Viscose', 'S, M, L','Green', 986556565, 'https://images.ua.prom.st/705057135_w800_h640_cid2327845_pid495526667-ee8b7bfe.jpg'),
(3, 'Shirt', 'jeans', '52, 54, 56, 58', 'Blue', 875756656, 'https://images.ua.prom.st/705093901_w800_h640_cid2327845_pid495533958-ff414cee.jpg'),
(4, 'Skirt', 'Leather', 'S, M, L, XL', 'Yellow', 6535656563, 'https://images.ua.prom.st/705095226_w800_h640_cid2327845_pid495533994-eb679f9b.jpg'),
(5, 'Shoes', 'leather', '40, 41, 42, 43, 44, 45', 'Black', 9765565656, 'https://images.ua.prom.st/705094977_w800_h640_cid2327845_pid495533989-e49fe064.jpg'),
(6, 'Pants', 'Jeans', 'S, M, L, XL', 'Blue', 6546512168589, 'https://images.ua.prom.st/705093676_w800_h640_cid2327845_pid495533956-21765ebf.jpg'),
(7, 'Skirt', 'leather', 'S, M, L, XL', 'Black', 6546512168589, 'https://images.ua.prom.st/340163413_w800_h640_zspighkdits1.jpg'),
(8, 'Suit', 'Velvet', 'S, M, L, XL', 'Red', 6546512168589, 'https://avenue-boutique.com.ua/media/catalog/product/cache/4/image/560x800/ea8fadf1dc16eb15153bb4d92cdfabcf/_/c/_carica_km-2063_1.jpg');

INSERT INTO Customer (id, first_Name, last_Name, user_Name, email, gender, phone, access, image) VALUES (1, 'John', 'Doe', 'JD', 'john@email.com', 'Men', 9898, true, 'https://www.cambio16.com/wp-content/uploads/2017/08/foto-el-nino-del-meme-triunfal-reaparece-10-anos-despues.jpg');
INSERT INTO Customer (id, first_Name, last_Name, user_Name, email, gender, phone, access, image) VALUES (2, 'Jon', 'Smith', 'SmithJ','smith@email.com','Men', 9865, true, 'https://image.flaticon.com/icons/svg/667/667189.svg');
INSERT INTO Customer (id, first_Name, last_Name, user_Name, email, gender, phone, access, image) VALUES (3, 'Will', 'Craig','CRWill', 'will@email.com', 'Men', 8757, true, 'https://image.flaticon.com/icons/svg/672/672642.svg');
INSERT INTO Customer (id, first_Name, last_Name, user_Name, email, gender, phone, access, image) VALUES (4, 'Sam', 'Lernorad','Leo', 'sam@email.com', 'Men', 6533, false, 'https://citaty.info/files/characters/7212.jpg');
INSERT INTO Customer (id, first_Name, last_Name, user_Name, email, gender, phone, access, image) VALUES (5, 'Semen', 'Lobanov','Lob', 'Lob@email.com', 'Men', 6933, true, 'https://citaty.info/files/characters/41918.jpg');
INSERT INTO Customer (id, first_Name, last_Name, user_Name, email, gender, phone, access, image) VALUES (6, 'Ross', 'Doe', 'DuRos','ross@email.com', 'Women', 9765, true, 'https://image.flaticon.com/icons/svg/672/672636.svg');

INSERT INTO Invoice (id, date_Of_Purchase, amount, currency, customer_id) VALUES (1, 889898498, 989.55, 'UAH', 1);
INSERT INTO Invoice (id, date_Of_Purchase, amount, currency, customer_id) VALUES (2, 098769898, 565.78, 'UAH', 1);
INSERT INTO Invoice (id, date_Of_Purchase, amount, currency, customer_id) VALUES (3, 883433898, 773.58, 'UAH', 1);

INSERT INTO Invoices_Products (invoice_id, product_id) VALUES (1, 1);
INSERT INTO Invoices_Products (invoice_id, product_id) VALUES (1, 2);
INSERT INTO Invoices_Products (invoice_id, product_id) VALUES (2, 1);
INSERT INTO Invoices_Products (invoice_id, product_id) VALUES (3, 1);

INSERT INTO AUTHORITY (id, role) VALUES (1, 'ANONYMOUS');
INSERT INTO AUTHORITY (id, role) VALUES (2, 'USER');
INSERT INTO AUTHORITY (id, role) VALUES (3, 'ADMIN');
INSERT INTO AUTHORITY (id, role) VALUES (4, 'SAD');

INSERT INTO users (id, username, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled, token) VALUES (5, 'anonymous', 'anonymous', true, true, true, true, 'anonymous:anonymous');
INSERT INTO users (id, username, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled, token) VALUES (1, 'username', 'password', true, true, true, true, 'username:password');
INSERT INTO users (id, username, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled, token) VALUES (2, 'administrator', 'adminPassword', true, true, true, true, 'administrator:adminPassword');
INSERT INTO users (id, username, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled, token) VALUES (3, 'superAdmin', 'superPass', true, true, true, true, 'superAdmin:superPass');
INSERT INTO users (id, username, password, account_Non_Expired, account_Non_Locked, credentials_Non_Expired, enabled, token) VALUES (9, 'Tomkats', 'Tomkatuser', true, true, true, true, 'Tomkats:Tomkatuser');

INSERT INTO user_authority (user_id, authority_id) VALUES (5, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (9, 2);