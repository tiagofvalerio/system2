CREATE TABLE test
(
  uuid        UUID                        NOT NULL,
  description CHARACTER VARYING(100)      NULL,
  CONSTRAINT test_pkey PRIMARY KEY (uuid)
);
