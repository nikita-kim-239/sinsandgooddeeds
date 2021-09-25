DROP TABLE IF EXISTS votes CASCADE;
DROP TABLE IF EXISTS acts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 100000;


CREATE TABLE public.users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  nick             VARCHAR                 NOT NULL,
  login            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL
);


CREATE TABLE public.acts(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  sin              BOOLEAN                NOT NULL,
  acted            DATE                   NOT NULL,
  description      VARCHAR                NOT NULL,
  user_id           INTEGER               NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE public.votes(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  time_of_vote     TIMESTAMP              NOT NULL,
  toheaven         BOOLEAN                NOT NULL,
  user_id          INTEGER                NOT NULL,
  target_user_id   INTEGER                NOT NULL,
  actual           BOOLEAN                NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (target_user_id) REFERENCES users (id) ON DELETE CASCADE
);