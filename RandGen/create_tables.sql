-- Table: profiles

-- DROP TABLE profiles;

CREATE TABLE profiles
(
  pid serial NOT NULL,
  min smallint,
  max smallint,
  seed smallint,
  "timestamp" timestamp with time zone NOT NULL,
  CONSTRAINT profiles_pkey PRIMARY KEY (pid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE profiles
  OWNER TO postgres;
GRANT ALL ON TABLE profiles TO postgres;
GRANT ALL ON TABLE profiles TO www;
