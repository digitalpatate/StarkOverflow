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
    author              TEXT    NOT NULL,
    CONSTRAINT     author
      FOREIGN KEY(author)
          REFERENCES users(user_id),
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
) ;

-- Answers
CREATE TABLE answers(
    answer_id              TEXT PRIMARY KEY NOT NULL UNIQUE,
    author              TEXT    NOT NULL,
    content         TEXT     NOT NULL,
    CONSTRAINT     author
        FOREIGN KEY(author)
            REFERENCES users(user_id),
    approuval_state BOOLEAN NOT NULL,
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);



-- comments

CREATE TABLE comments_on_questions(
    comment_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
    author               TEXT    NOT NULL,
    content              TEXT    NOT NULL,
    fk_question_id         TEXT    NOT NULL,
    CONSTRAINT     fk_question_id
      FOREIGN KEY(fk_question_id)
          REFERENCES questions(question_id),
    CONSTRAINT     author
      FOREIGN KEY(author)
          REFERENCES users(user_id),
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);

CREATE TABLE comments_on_answers(
    comment_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
    author               TEXT    NOT NULL,
    content              TEXT    NOT NULL,
    fk_answer_id         TEXT    NOT NULL,
    CONSTRAINT     fk_answer_id
        FOREIGN KEY(fk_answer_id)
            REFERENCES answers(answer_id),
    CONSTRAINT     author
        FOREIGN KEY(author)
            REFERENCES users(user_id),
    creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);

-- votes
CREATE TABLE votes_on_questions(
    vote_on_question_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
    author              TEXT    NOT NULL,
    fk_question_id      TEXT    NOT NULL,
    CONSTRAINT     author
       FOREIGN KEY(author)
           REFERENCES users(user_id),
    CONSTRAINT     fk_question_id
       FOREIGN KEY(fk_question_id)
           REFERENCES questions(question_id),
    creationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);

CREATE TABLE votes_on_answers(
vote_on_answer_id TEXT PRIMARY KEY     NOT NULL UNIQUE,
author              TEXT    NOT NULL,
fk_answer_id        TEXT    NOT NULL,
CONSTRAINT     author
    FOREIGN KEY(author)
        REFERENCES users(user_id),
CONSTRAINT     fk_answer_id
    FOREIGN KEY(fk_answer_id)
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
    tag_id TEXT REFERENCES tags(tag_id) ON UPDATE CASCADE ON DELETE CASCADE,
    question_id TEXT REFERENCES questions(question_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT tags_questions_pkey PRIMARY KEY (tag_id, question_id)
);