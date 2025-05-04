INSERT INTO products_bak ( ID , NAME ,  PRICE ,  QUANTITY ,  SKU ,  ACTIVE)
 SELECT  ID , NAME ,  PRICE ,  QUANTITY ,  SKU ,  [ACTIVE]
  FROM products
   WHERE ID = {ID};
-- [] is optional parameter(s) & {} is required parameter(s)
-- [] can be null as ...,AGE,null