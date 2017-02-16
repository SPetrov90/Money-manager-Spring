DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS operation;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START with 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR(255),
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  registered TIMESTAMP NOT NULL DEFAULT now(),
  enabled    BOOLEAN  DEFAULT TRUE,
  balance    INT
);
CREATE UNIQUE INDEX unique_email ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE operation (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  datetime    TIMESTAMP,
  description VARCHAR(255),
  operation_type VARCHAR(255),
  type VARCHAR(255),
  money_count    INT,
  user_id INTEGER NOT NULL,
  FOREIGN KEY ( user_id ) REFERENCES USERS ( id ) ON DELETE CASCADE
);

