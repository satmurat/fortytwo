CREATE TABLE "user" (
	id uuid NOT NULL,
	display_name varchar(50) NOT NULL,
	email varchar(100) NOT NULL,
	password varchar(255) NOT NULL,
	created_at timestamp NOT NULL,
	roles varchar(255) NOT NULL DEFAULT 'user',
	CONSTRAINT user_pk PRIMARY KEY (id),
	CONSTRAINT user_un UNIQUE (email)
);

ALTER TABLE question DROP COLUMN author;
ALTER TABLE question ADD user_id uuid;
ALTER TABLE question ADD CONSTRAINT question_user_fk FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE SET NULL ON UPDATE CASCADE ;

ALTER TABLE answer DROP COLUMN author;
ALTER TABLE answer ADD user_id uuid;
ALTER TABLE answer ADD CONSTRAINT answer_user_fk FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE SET NULL ON UPDATE CASCADE ;
