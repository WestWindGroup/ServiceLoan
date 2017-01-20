-- users
CREATE TABLE IF NOT EXISTS users (
  id         SERIAL       NOT NULL PRIMARY KEY,
  login      VARCHAR(50)  NOT NULL,
  first_name VARCHAR(50)  NOT NULL,
  last_name  VARCHAR(50)  NOT NULL,
  password   VARCHAR(255) NOT NULL
);

-- roles
CREATE TABLE IF NOT EXISTS roles (
  id   SERIAL      NOT NULL PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

-- user_roles
CREATE TABLE IF NOT EXISTS user_roles (
  user_id SERIAL NOT NULL,
  role_id SERIAL NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),

  UNIQUE (user_id, role_id)
);

-- clients
CREATE TABLE IF NOT EXISTS clients (
  id                SERIAL      NOT NULL PRIMARY KEY,
  first_name        VARCHAR(50) NOT NULL,
  last_name         VARCHAR(50) NOT NULL,
  registration_date TIMESTAMP   NOT NULL,
  birth_date        TIMESTAMP   NOT NULL
);

-- credits
CREATE TABLE IF NOT EXISTS credits (
  id        SERIAL         NOT NULL PRIMARY KEY,
  amount    NUMERIC(19, 2) NOT NULL,
  debt      NUMERIC(19, 2) NOT NULL,
  open_date TIMESTAMP      NOT NULL,
  shut_date TIMESTAMP      NOT NULL
);

-- client_credits
CREATE TABLE IF NOT EXISTS client_credits (
  client_id SERIAL NOT NULL,
  credit_id SERIAL NOT NULL,

  FOREIGN KEY (client_id) REFERENCES clients (id),
  FOREIGN KEY (credit_id) REFERENCES credits (id),

  UNIQUE (client_id, credit_id)
);

-- statuses
CREATE TABLE IF NOT EXISTS statuses (
  id     SERIAL      NOT NULL PRIMARY KEY,
  status VARCHAR(50) NOT NULL
);

-- credit_status
CREATE TABLE IF NOT EXISTS credit_status (
  credit_id SERIAL NOT NULL,
  status_id SERIAL NOT NULL,

  FOREIGN KEY (credit_id) REFERENCES credits (id),
  FOREIGN KEY (status_id) REFERENCES statuses (id),

  UNIQUE (credit_id, status_id)
);

-- rate_interest
CREATE TABLE IF NOT EXISTS rate_interest (
  id   SERIAL           NOT NULL PRIMARY KEY,
  rate DOUBLE PRECISION NOT NULL
);

-- credit_rate
CREATE TABLE IF NOT EXISTS credit_rate (
  credit_id SERIAL NOT NULL,
  rate_id   SERIAL NOT NULL,

  FOREIGN KEY (credit_id) REFERENCES credits (id),
  FOREIGN KEY (rate_id) REFERENCES rate_interest (id),

  UNIQUE (credit_id, rate_id)
);

-- duration
CREATE TABLE IF NOT EXISTS duration (
  id       SERIAL NOT NULL PRIMARY KEY,
  duration INT    NOT NULL
);


-- credit_duration
CREATE TABLE IF NOT EXISTS credit_duration (
  credit_id SERIAL NOT NULL,
  duration_id   SERIAL NOT NULL,

  FOREIGN KEY (credit_id) REFERENCES credits (id),
  FOREIGN KEY (duration_id) REFERENCES duration (id),

  UNIQUE (credit_id, duration_id)
);

-- types
CREATE TABLE IF NOT EXISTS types (
  id   SERIAL      NOT NULL PRIMARY KEY,
  type VARCHAR(50) NOT NULL
);

-- credit_type
CREATE TABLE IF NOT EXISTS credit_type (
  credit_id SERIAL NOT NULL,
  type_id   SERIAL NOT NULL,

  FOREIGN KEY (credit_id) REFERENCES credits (id),
  FOREIGN KEY (type_id) REFERENCES types (id),

  UNIQUE (credit_id, type_id)
);



