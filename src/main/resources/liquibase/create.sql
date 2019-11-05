--liquibase formatted sql
--changeset splitStatements:true
DROP TABLE IF EXISTS question;

DROP TABLE IF EXISTS vote;

CREATE SEQUENCE IF NOT EXISTS vote_id_seq;

CREATE TABLE vote
(
    id integer NOT NULL DEFAULT nextval('vote_id_seq'),
    name character varying(255),
    date_from date,
    date_to date,
    active boolean,
    CONSTRAINT vote_pk PRIMARY KEY (id)
);

ALTER SEQUENCE vote_id_seq OWNED BY vote.id;

CREATE TABLE question
(
    id integer references vote(id),
    text character varying(255),
    ordering integer,
    CONSTRAINT question_pk PRIMARY KEY (id)
);
