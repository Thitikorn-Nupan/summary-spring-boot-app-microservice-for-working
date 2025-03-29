-- clear rows & auto increment
-- note! in h2 db have to use @FOREIGN_KEY_CHECKS
SET @FOREIGN_KEY_CHECKS = 0;
TRUNCATE table PUBLIC.USERS RESTART IDENTITY; -- RESTART IDENTITY for reset auto increment
SET @FOREIGN_KEY_CHECKS = 1;


INSERT INTO PUBLIC.USERS (USERNAME, MAIL) VALUES ('adam', 'adam@abc.abc'),
                                                 ('kevin', 'kevin@abc.abc'),
                                                 ('austin', 'austin@abc.abc'),
                                                 ('jame', 'jame@hotmail.com'),
                                                 ('wong', 'wong@hotmail.com') ;


