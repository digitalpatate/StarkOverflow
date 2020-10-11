-- Database: stark_db

DROP DATABASE IF EXISTS stark_db;


CREATE DATABASE stark_db;
GRANT ALL PRIVILEGES ON DATABASE stark_db TO admin;

-- connect to stark_db
\c stark_db

-- set timezone to UTC+2
set time zone 'Europe/Zurich';

CREATE TABLE users(
   id              TEXT PRIMARY KEY    NOT NULL,
   email           TEXT    NOT NULL,
   profilePictureURL         TEXT   ,
   firstname           TEXT    NOT NULL,
   lastname         TEXT    NOT NULL,
   username         TEXT     NOT NULL,
   password         TEXT     NOT NULL,
   registrationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)  
);

CREATE TABLE commentables_votalbes(
   id              TEXT PRIMARY KEY    NOT NULL,
   content         TEXT     NOT NULL,
   creationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)  
);


CREATE TABLE questions(
   author              TEXT    NOT NULL,
   CONSTRAINT     author
      FOREIGN KEY(author)
         REFERENCES users(id),
   title           TEXT    NOT NULL
   ) INHERITS(commentables_votalbes);

CREATE TABLE answers(
   author              TEXT    NOT NULL,
   CONSTRAINT     author
      FOREIGN KEY(author)
         REFERENCES users(id),
   approuval_state BOOLEAN NOT NULL
) INHERITS(commentables_votalbes);


CREATE TABLE comments(
   id TEXT PRIMARY KEY     NOT NULL,
   author              TEXT    NOT NULL,
   content         TEXT     NOT NULL,
   CONSTRAINT     author
      FOREIGN KEY(id)
         REFERENCES users(id),
   CONSTRAINT     fk_commentable
      FOREIGN KEY(id)
         REFERENCES commentables_votalbes(id),
   creationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)     
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
   author              TEXT    NOT NULL,  
   CONSTRAINT     author
      FOREIGN KEY(id)
         REFERENCES users(id),
   CONSTRAINT     fk_votable
      FOREIGN KEY(id)
         REFERENCES commentables_votalbes(id),
   creationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)     
);