INSERT INTO products ( NAME ,  PRICE ,  QUANTITY ,  SKU ,  ACTIVE ) VALUES
                                                                          ( 'Puppy Chicken', 3500.00, 100, 'MX-123-965LD', 1),
                                                                          ( 'Fofos Woodplay', 135.00, 100, 'FX-103-965LR', 1),
                                                                          ( 'VETRESKA Coffee Pet', 355.00, 100, 'VT-103-965VR', 1),
                                                                          ( 'Pet’O 210ml.', 705.00, 100, 'PT-203-965PP', 1),
                                                                          ({NAME} , {PRICE} , {QUANTITY} , {SKU} , {ACTIVE});
-- Remember JDBC template or the Jdbc driver doesn't allow truncate operations so you have to do on script not statement

