INSERT INTO customer VALUES(1, 'Putka Oy', 'Vankilakatu 1', '00100', 'Helsinki', 'Finland');
INSERT INTO customer VALUES(2, 'Putka Oy 2', 'Vankilakatu 2', '00200', 'Tallinn', 'Estonia');
INSERT INTO orders VALUES(order_seq.NEXTVAL, 1);
INSERT INTO orders VALUES(order_seq.NEXTVAL, 1);
INSERT INTO order_line VALUES(order_line_seq.NEXTVAL, 1, '1', 'Nokia Lumia 1020', 1, 11.11);

INSERT INTO order_line VALUES(order_line_seq.NEXTVAL, 2, '2', 'Nokia Lumia 1520', 1, 10.10);