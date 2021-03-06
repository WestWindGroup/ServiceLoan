-- users
INSERT INTO USERS VALUES (1,  'admin', 'Admin', 'Admin', '$2a$06$/CKzkal2v7y/auceMWbP/ue7ehknc2oU/aKyzgdFJ8ND91HmruU7C');
INSERT INTO USERS VALUES (2,  'user1', 'User', 'User', '$2a$06$7WXNEWw1o9CL74yGv1VyJ.RPFzqMidfoT56/2qYU0Vga767pObxpy');
INSERT INTO USERS VALUES (3,  'user2', 'User', 'User', '$2a$06$7WXNEWw1o9CL74yGv1VyJ.RPFzqMidfoT56/2qYU0Vga767pObxpy');
INSERT INTO USERS VALUES (4,  'user3', 'User', 'User', '$2a$06$7WXNEWw1o9CL74yGv1VyJ.RPFzqMidfoT56/2qYU0Vga767pObxpy');
INSERT INTO USERS VALUES (5,  'user4', 'User', 'User', '$2a$06$7WXNEWw1o9CL74yGv1VyJ.RPFzqMidfoT56/2qYU0Vga767pObxpy');

-- roles
INSERT INTO ROLES VALUES (1, 'ROLE_USER');
INSERT INTO ROLES VALUES (2, 'ROLE_ADMIN');
INSERT INTO ROLES VALUES (3, 'ROLE3');
INSERT INTO ROLES VALUES (4, 'ROLE4');
INSERT INTO ROLES VALUES (5, 'ROLE5');

-- user_roles
INSERT INTO USER_ROLES VALUES (1, 2);
INSERT INTO USER_ROLES VALUES (2, 1);
INSERT INTO USER_ROLES VALUES (3, 1);
INSERT INTO USER_ROLES VALUES (4, 1);
INSERT INTO USER_ROLES VALUES (5, 1);

-- statuses
INSERT INTO STATUSES VALUES (1, 'status1');
INSERT INTO STATUSES VALUES (2, 'status2');
INSERT INTO STATUSES VALUES (3, 'status3');
INSERT INTO STATUSES VALUES (4, 'status4');
INSERT INTO STATUSES VALUES (5, 'status5');

-- rate_interest
INSERT INTO RATE_INTEREST VALUES (1, 35.50);
INSERT INTO RATE_INTEREST VALUES (2, 31.50);
INSERT INTO RATE_INTEREST VALUES (3, 25.00);
INSERT INTO RATE_INTEREST VALUES (4, 35.50);
INSERT INTO RATE_INTEREST VALUES (5, 31.50);

-- duration
INSERT INTO DURATION VALUES (1, 12);
INSERT INTO DURATION VALUES (2, 24);
INSERT INTO DURATION VALUES (3, 36);
INSERT INTO DURATION VALUES (4, 48);
INSERT INTO DURATION VALUES (5, 60);

-- types
INSERT INTO TYPES VALUES (1, 'type1');
INSERT INTO TYPES VALUES (2, 'type2');
INSERT INTO TYPES VALUES (3, 'type3');
INSERT INTO TYPES VALUES (4, 'type4');
INSERT INTO TYPES VALUES (5, 'type5');

-- Client
INSERT INTO CLIENTS(id,first_name,last_name) VALUES (1, 'Test1','Tester1');
INSERT INTO CLIENTS(id,first_name,last_name) VALUES (2, 'Test2','Tester2');
INSERT INTO CLIENTS(id,first_name,last_name) VALUES (3, 'Test3','Tester3');
INSERT INTO CLIENTS(id,first_name,last_name) VALUES (4, 'Test4','Tester4');
INSERT INTO CLIENTS(id,first_name,last_name) VALUES (5, 'Test5','Tester5');

-- Credits
INSERT INTO CREDITS(id,amount,debt) VALUES (1, 5000, 5000);
INSERT INTO CREDITS(id,amount,debt) VALUES (2, 10000, 10000);
INSERT INTO CREDITS(id,amount,debt) VALUES (3, 5000, 5000);
INSERT INTO CREDITS(id,amount,debt) VALUES (4, 10000, 10000);
INSERT INTO CREDITS(id,amount,debt) VALUES (5, 5000, 5000);

-- Payment
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (1, 500, 100, 600);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (2, 550, 150, 700);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (3, 555, 155, 710);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (4, 600, 100, 700);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (5, 650, 150, 800);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (6, 655, 155, 810);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (7, 700, 100, 800);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (8, 750, 150, 900);
INSERT INTO PAYMENT(id,amount_payment,body_credit,rate_payment) VALUES (9, 755, 155, 910);

-- Credit Payment
INSERT INTO CREDIT_PAYMENT VALUES (1, 1);
INSERT INTO CREDIT_PAYMENT VALUES (1, 2);
INSERT INTO CREDIT_PAYMENT VALUES (2, 3);
INSERT INTO CREDIT_PAYMENT VALUES (2, 4);
INSERT INTO CREDIT_PAYMENT VALUES (3, 5);
INSERT INTO CREDIT_PAYMENT VALUES (3, 6);
INSERT INTO CREDIT_PAYMENT VALUES (4, 7);
INSERT INTO CREDIT_PAYMENT VALUES (4, 8);
INSERT INTO CREDIT_PAYMENT VALUES (5, 9);

-- Credit status
INSERT INTO CREDIT_STATUS VALUES (1, 1);
INSERT INTO CREDIT_STATUS VALUES (2, 1);
INSERT INTO CREDIT_STATUS VALUES (3, 1);
INSERT INTO CREDIT_STATUS VALUES (4, 1);
INSERT INTO CREDIT_STATUS VALUES (5, 1);

-- Credit types
INSERT INTO CREDIT_TYPE VALUES (1, 1);
INSERT INTO CREDIT_TYPE VALUES (2, 1);
INSERT INTO CREDIT_TYPE VALUES (3, 1);
INSERT INTO CREDIT_TYPE VALUES (4, 1);
INSERT INTO CREDIT_TYPE VALUES (5, 1);

-- Credit rate
INSERT INTO CREDIT_RATE VALUES (1, 1);
INSERT INTO CREDIT_RATE VALUES (2, 2);
INSERT INTO CREDIT_RATE VALUES (3, 1);
INSERT INTO CREDIT_RATE VALUES (4, 2);
INSERT INTO CREDIT_RATE VALUES (5, 1);

-- Credit duration
INSERT INTO CREDIT_DURATION VALUES (1, 1);
INSERT INTO CREDIT_DURATION VALUES (2, 2);
INSERT INTO CREDIT_DURATION VALUES (3, 1);
INSERT INTO CREDIT_DURATION VALUES (4, 2);
INSERT INTO CREDIT_DURATION VALUES (5, 1);

-- client credit
INSERT INTO CLIENT_CREDITS VALUES (1, 1);
INSERT INTO CLIENT_CREDITS VALUES (2, 2);
INSERT INTO CLIENT_CREDITS VALUES (3, 3);
INSERT INTO CLIENT_CREDITS VALUES (4, 4);
INSERT INTO CLIENT_CREDITS VALUES (5, 5);