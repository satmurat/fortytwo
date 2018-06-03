CREATE TABLE public.question (
	id uuid NOT NULL,
	title varchar(300) NOT NULL,
	body varchar(10000) NOT NULL,
	author varchar(100) NOT NULL,
	category varchar(100) NOT NULL,
	created_at timestamp NOT NULL,
	CONSTRAINT question_pk PRIMARY KEY (id)
);

CREATE TABLE public.answer (
	id uuid NOT NULL,
	body varchar(10000) NOT NULL,
	author varchar(100) NOT NULL,
	created_at timestamp NOT NULL,
	question_id uuid NOT NULL,
	CONSTRAINT answer_pk PRIMARY KEY (id),
	CONSTRAINT answer_question_fk FOREIGN KEY (question_id) REFERENCES public.question(id) ON DELETE CASCADE ON UPDATE CASCADE
);
