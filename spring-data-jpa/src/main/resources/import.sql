INSERT INTO Address (id, city, street, street_number, postal_code) VALUES (1, 'Wrocław', 'Rynek', '44', '55-555');
INSERT INTO Address (id, city, street, street_number, postal_code) VALUES (2, 'Wrocław', 'Strzegomska', '42', '55-551');

INSERT INTO Library (id, name, address_id, version) VALUES (1, 'Biblioteka Rynek', 1, 1);
INSERT INTO Library (id, name, address_id, version) VALUES (2, 'Biblioteka Miejska', 2, 1);

INSERT INTO Book (id, title, library_id) VALUES (1, 'Sample Book', 1);
INSERT INTO Book (id, title, library_id) VALUES (2, 'Second Book', 2);
INSERT INTO Book (id, title, library_id) VALUES (3, 'Third Book', 2);

INSERT INTO Person (id, firstName, lastName, age) VALUES (1, 'Jan', 'Kowalski', 44);
INSERT INTO Person (id, firstName, lastName, age) VALUES (2, 'Marian', 'Nowak', 23);
INSERT INTO Author (author_id, publications) VALUES (1, 15);
INSERT INTO Author (author_id, publications) VALUES (2, 11);

INSERT INTO book_author (BOOK_ID, AUTHOR_ID) VALUES (1, 1);
INSERT INTO book_author (BOOK_ID, AUTHOR_ID) VALUES (2, 1);
INSERT INTO book_author (BOOK_ID, AUTHOR_ID) VALUES (2, 2);
INSERT INTO book_author (BOOK_ID, AUTHOR_ID) VALUES (3, 2);

INSERT INTO company (id, NIP, REGON, account, city, eMail, name, phoneNumber, postCode, street, version) VALUES (1, '6680009781', NULL, '10500', 'Turek', 'first@gmail.com', 'firstCustomer', '632781819', '62700', 'Turek', 1);

INSERT INTO customer (company_id) VALUES (1);

INSERT INTO invoice (id, creationDate, paymentDeadline, remarks, saleDate, CUSTOMER_ID) VALUES (1, '2015-01-01', '2016-01-01', 'remarks Przykładowe', '2015-03-03', 1);

INSERT INTO tax_rate VALUES (1, 23.0, '23');
INSERT INTO tax_rate VALUES (2, 8.0, '8');

INSERT INTO product (id, measureUnit, name, pkwiu, CURRENCY, AMOUNT, taxRate_TAX_RATE_VALUE) VALUES (1, 'KG', 'ser', '1234', 'PLN', 4.5, 23);
INSERT INTO product (id, measureUnit, name, pkwiu, CURRENCY, AMOUNT, taxRate_TAX_RATE_VALUE) VALUES (2, 'KG', 'mleko', 'kwui', 'PLN', 12.50, 8);
INSERT INTO product (id, measureUnit, name, pkwiu, CURRENCY, AMOUNT, taxRate_TAX_RATE_VALUE) VALUES (3, 'SZT', 'zabawka', 'zabawkapkwiu', 'PLN', 1234.40, 23);
INSERT INTO item (INVOICE_ID, PRODUCT_ID, quantity) VALUES (1, 1, 2.5);
INSERT INTO item (INVOICE_ID, PRODUCT_ID, quantity) VALUES (1, 2, 10.54);
INSERT INTO item (INVOICE_ID, PRODUCT_ID, quantity) VALUES (1, 3, 2);
