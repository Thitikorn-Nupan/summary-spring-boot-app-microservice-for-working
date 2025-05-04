-- clear rows & auto increment
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE table products;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO products ( NAME ,  PRICE ,  QUANTITY ,  SKU ,  ACTIVE ) VALUES
                                                                          ( 'Puppy Chicken', 3500.00, 100, 'MX-123-965LD', 1),
                                                                          ( 'Fofos Woodplay', 135.00, 100, 'FX-103-965LR', 1),
                                                                          ( 'VETRESKA Coffee Pet', 355.00, 100, 'VT-103-965VR', 1),
                                                                          ( 'Pet’O 210ml.', 705.00, 100, 'PT-203-965PP', 1),
                                                                          ( 'CERA Waterless', 700.00, 100, 'CX-123-C65LD', 1);
