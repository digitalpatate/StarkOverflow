-- Database: stark_db

DROP DATABASE IF EXISTS stark_db;


CREATE DATABASE stark_db;
GRANT ALL PRIVILEGES ON DATABASE stark_db TO admin;

-- connect to stark_db
\c stark_db
-- Users
CREATE TABLE users(
    user_id              TEXT PRIMARY KEY NOT NULL UNIQUE,
    email           TEXT    NOT NULL,
    profile_picture_url         TEXT   ,
    firstname           TEXT    NOT NULL,
    lastname         TEXT    NOT NULL,
    username         TEXT     NOT NULL,
    password         TEXT     NOT NULL,
    registration_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);

-- Questions
CREATE TABLE questions(
    question_id              TEXT PRIMARY KEY NOT NULL UNIQUE,
    title           TEXT    NOT NULL,
    content         TEXT     NOT NULL,
    fk_author              TEXT    NOT NULL,
    CONSTRAINT     fk_author
      FOREIGN KEY(fk_author)
          REFERENCES users(user_id),
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
) ;

-- Answers
CREATE TABLE answers(
    answer_id              TEXT PRIMARY KEY NOT NULL UNIQUE,
    fk_author              TEXT    NOT NULL,
    content         TEXT     NOT NULL,
    fk_question     text    NOT NULL,
    CONSTRAINT     fk_author
        FOREIGN KEY(fk_author)
            REFERENCES users(user_id),
    CONSTRAINT     fk_question
        FOREIGN KEY(fk_question)
            REFERENCES questions(question_id),
    approuval_state BOOLEAN NOT NULL DEFAULT FALSE,
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);



-- comments on answers

CREATE TABLE comments(
    comment_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
    fk_author               TEXT    NOT NULL,
    content              TEXT    NOT NULL,
    fk_answer         TEXT    NOT NULL,
    CONSTRAINT     fk_answer
        FOREIGN KEY(fk_answer)
            REFERENCES answers(answer_id),
    CONSTRAINT     fk_author
        FOREIGN KEY(fk_author)
            REFERENCES users(user_id),
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);

-- votes

CREATE TABLE votes(
vote_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
fk_author              TEXT    NOT NULL,
fk_answer       TEXT    NOT NULL,
CONSTRAINT     fk_author
    FOREIGN KEY(fk_author)
        REFERENCES users(user_id),
CONSTRAINT     fk_answer
    FOREIGN KEY(fk_answer)
        REFERENCES answers(answer_id),
creationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);



-- Tags
CREATE TABLE tags(
    tag_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
    name         TEXT     NOT NULL,
    color         TEXT    NOT NULL
);

CREATE TABLE tags_questions(
    fk_tag TEXT REFERENCES tags(tag_id),
    fk_question TEXT REFERENCES questions(question_id),
    CONSTRAINT tags_questions_pkey PRIMARY KEY (fk_tag, fk_question)
);