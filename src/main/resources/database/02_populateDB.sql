-- users
INSERT INTO users VALUES (DEFAULT,  'admin', 'Admin', 'Admin', '$2a$06$AF2RBN/gBqI5rlGElCv3hOy0NiEuqJzZCcwbN59/ITmhsQv5FpZKK');
INSERT INTO users VALUES (DEFAULT,  'operator', 'Operator', 'Operator', '$2a$06$6GJxEVt/bcx0uawXvM.9e.uAEi9xClDAZby83hbcmPRvtsD2JHsfC');

-- roles
INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

-- user_roles
INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (1, 2);

