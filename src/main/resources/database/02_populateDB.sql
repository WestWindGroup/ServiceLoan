-- users
INSERT INTO users VALUES (DEFAULT,  'admin', 'Admin', 'Admin', '$2a$06$/CKzkal2v7y/auceMWbP/ue7ehknc2oU/aKyzgdFJ8ND91HmruU7C');
INSERT INTO users VALUES (DEFAULT,  'user', 'User', 'User', '$2a$06$7WXNEWw1o9CL74yGv1VyJ.RPFzqMidfoT56/2qYU0Vga767pObxpy');

-- roles
INSERT INTO roles VALUES (DEFAULT, 'ROLE_USER');
INSERT INTO roles VALUES (DEFAULT, 'ROLE_ADMIN');

-- user_roles
INSERT INTO user_roles VALUES (1, 2);
INSERT INTO user_roles VALUES (2, 1);

-- statuses
INSERT INTO statuses VALUES (DEFAULT, 'repaid');
INSERT INTO statuses VALUES (DEFAULT, 'active');

-- rate_interest
INSERT INTO rate_interest VALUES (DEFAULT, 35.50);
INSERT INTO rate_interest VALUES (DEFAULT, 31.50);
INSERT INTO rate_interest VALUES (DEFAULT, 25.00);

-- duration
INSERT INTO duration VALUES (DEFAULT, 12);
INSERT INTO duration VALUES (DEFAULT, 24);
INSERT INTO duration VALUES (DEFAULT, 36);

-- types
INSERT INTO duration VALUES (DEFAULT, 'Differentiated');
INSERT INTO duration VALUES (DEFAULT, 'Annuity');


