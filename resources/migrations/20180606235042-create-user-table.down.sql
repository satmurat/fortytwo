ALTER TABLE question DROP COLUMN user_id;
ALTER TABLE question ADD author varchar(100);

ALTER TABLE answer DROP COLUMN user_id;
ALTER TABLE answer ADD author varchar(100);

DROP TABLE "user";
