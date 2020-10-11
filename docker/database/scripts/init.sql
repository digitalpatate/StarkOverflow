-- Database: stark_db

DROP DATABASE IF EXISTS stark_db;


CREATE DATABASE stark_db;
GRANT ALL PRIVILEGES ON DATABASE stark_db TO admin;

-- connect to stark_db
\c stark_db

CREATE TABLE users(
   user_id              TEXT PRIMARY KEY    NOT NULL,
   email           TEXT    NOT NULL,
   profile_picture_url         TEXT   ,
   firstname           TEXT    NOT NULL,
   lastname         TEXT    NOT NULL,
   username         TEXT     NOT NULL,
   password         TEXT     NOT NULL,
   registration_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);

CREATE TABLE commentables_votables(
    -- Question & answer id
   qa_id              TEXT PRIMARY KEY    NOT NULL,
   content         TEXT     NOT NULL,
   creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);


CREATE TABLE questions(
   author              TEXT    NOT NULL,
   CONSTRAINT     author
      FOREIGN KEY(author)
         REFERENCES users(user_id),
   title           TEXT    NOT NULL
   ) INHERITS(commentables_votables);

CREATE TABLE answers(
   author              TEXT    NOT NULL,
   CONSTRAINT     author
      FOREIGN KEY(author)
         REFERENCES users(user_id),
   approuval_state BOOLEAN NOT NULL
) INHERITS(commentables_votables);


CREATE TABLE comments(
   comment_id TEXT PRIMARY KEY     NOT NULL,
   author              TEXT    NOT NULL,
   content         TEXT     NOT NULL,
   CONSTRAINT     author
      FOREIGN KEY(author)
         REFERENCES users(user_id),
   CONSTRAINT     fk_commentable
      FOREIGN KEY(comment_id)
         REFERENCES commentables_votables(qa_id),
   creation_date     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)
);


CREATE TABLE tags(
   tag_id TEXT PRIMARY KEY     NOT NULL,
   name         TEXT     NOT NULL,
   color         TEXT    NOT NULL  
);

CREATE TABLE tags_questions(
   tag_id TEXT REFERENCES tags(tag_id) ON UPDATE CASCADE ON DELETE CASCADE,
   question_id TEXT REFERENCES commentables_votables(qa_id) ON UPDATE CASCADE ON DELETE CASCADE,
   CONSTRAINT tags_questions_pkey PRIMARY KEY (tag_id, question_id)
);



CREATE TABLE votes(
   vote_id TEXT PRIMARY KEY     NOT NULL,
   author              TEXT    NOT NULL,  
   CONSTRAINT     author
      FOREIGN KEY(author)
         REFERENCES users(user_id),
   CONSTRAINT     fk_votable
      FOREIGN KEY(vote_id)
         REFERENCES commentables_votables(qa_id),
   creationDate     TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP(3)     
);