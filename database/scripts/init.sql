-- Database: stark_db

DROP DATABASE IF EXISTS stark_db;


CREATE DATABASE stark_db;
GRANT ALL PRIVILEGES ON DATABASE stark_db TO admin;

-- connect to stark_db
\c stark_db

CREATE TABLE users(
   id              TEXT PRIMARY KEY    NOT NULL,
   email           TEXT    NOT NULL,
   username         TEXT     NOT NULL,
   picture_url          INT,
   firstname           TEXT    NOT NULL,
   lastname         TEXT    NOT NULL,
   creation_date   TIMESTAMP    
);

CREATE TABLE commentables_votalbes(
   id              TEXT PRIMARY KEY    NOT NULL,
   content         TEXT     NOT NULL,
   CONSTRAINT     fk_user
      FOREIGN KEY(id)
         REFERENCES users(id),
   creation_date   TIMESTAMP
);


CREATE TABLE questions(
   title           TEXT    NOT NULL
   ) INHERITS(commentables_votalbes);

CREATE TABLE answers(  
   approuval_state BOOLEAN NOT NULL
) INHERITS(commentables_votalbes);


CREATE TABLE comments(
   id TEXT PRIMARY KEY     NOT NULL,
   content         TEXT     NOT NULL,
   CONSTRAINT     fk_user
      FOREIGN KEY(id)
         REFERENCES users(id),
   CONSTRAINT     fk_commentable
      FOREIGN KEY(id)
         REFERENCES commentables_votalbes(id),
   creation_date    TIMESTAMP    
);


CREATE TABLE tags(
   id TEXT PRIMARY KEY     NOT NULL,
   name         TEXT     NOT NULL,
   color         TEXT    NOT NULL  
);

CREATE TABLE tags_questions(
   tag_id TEXT REFERENCES tags(id) ON UPDATE CASCADE ON DELETE CASCADE,
   question_id TEXT REFERENCES commentables_votalbes(id) ON UPDATE CASCADE ON DELETE CASCADE,
   CONSTRAINT tags_questions_pkey PRIMARY KEY (tag_id, question_id)
);



CREATE TABLE votes(
   id TEXT PRIMARY KEY     NOT NULL,
   CONSTRAINT     fk_user
      FOREIGN KEY(id)
         REFERENCES users(id),
   CONSTRAINT     fk_votable
      FOREIGN KEY(id)
         REFERENCES commentables_votalbes(id),
   DATE TIMESTAMP NOT NULL
);