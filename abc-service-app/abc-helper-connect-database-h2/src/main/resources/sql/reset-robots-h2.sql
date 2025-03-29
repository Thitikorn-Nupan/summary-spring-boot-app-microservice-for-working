-- clear rows & auto increment
-- clear rows & auto increment
-- note! in h2 db have to use @FOREIGN_KEY_CHECKS
SET @FOREIGN_KEY_CHECKS = 0;
TRUNCATE table PUBLIC.ROBOTS RESTART IDENTITY; -- RESTART IDENTITY for reset auto increment
SET @FOREIGN_KEY_CHECKS = 1;

INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (1, 'Wall-E', '2008-06-27', 1500, true);
INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (2, 'R2-D2', '1977-05-25', 2000.5, true);
INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (3, 'C-3PO', '1977-05-25', 1800.75, true);
INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (4, 'T-800', '1984-10-26', 3000, false);
INSERT INTO PUBLIC.ROBOTS (RID, CODENAME, RELEASEDATE, PRICE, STATUS) VALUES (5, 'Optimus Prime', '2007-07-03', 5000, true);
