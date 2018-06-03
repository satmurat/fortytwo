-- :name insert-question :! :n
-- :doc Insert a single question
insert into question (id, body, author, category, created_at)
values (:id, :body, :author, :category, CURRENT_TIMESTAMP);

-- :name insert-answer :! :n
-- :doc Insert a single answer
insert into answer (id, body, author, question_id, created_at)
values (:id, :body, :author, :question_id, CURRENT_TIMESTAMP);

-- :name all-questions :? :*
-- :doc Get all questions
select q.id qid, q.created_at qcreated_at, q.category qcategory, q.title qtitle, q.author qauthor,
count(a.id) answers_count
 from question q join answer a on q.id = a.question_id
group by qid
order by q.created_at desc limit :limit;

-- :name question :? :1
-- :doc Get all questions
select q.id qid, q.created_at qcreated_at, q.category qcategory, q.body qbody, q.author qauthor,
 a.id aid, a.created_at acreated_at, a.body abody, a.author aauthor from question q
left join answer a on q.id = a.question_id
where q.id = :id;
