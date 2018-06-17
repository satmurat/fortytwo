-- :name insert-question :! :n
-- :doc Insert a single question
insert into question (id, body, user_id, category, created_at)
values (:id, :body, :user_id, :category, CURRENT_TIMESTAMP);

-- :name insert-answer :! :n
-- :doc Insert a single answer
insert into answer (id, body, user_id, question_id, created_at)
values (:id, :body, :author, :question_id, CURRENT_TIMESTAMP);

-- :name all-questions :? :*
-- :doc Get all questions
select q.id qid, q.created_at qcreated_at, q.category qcategory, q.title qtitle, u.display_name qauthor,
count(a.id) answers_count
 from question q join answer a on q.id = a.question_id
 join "user" u on q.user_id=u.id
group by qid, qauthor
order by q.created_at desc limit :limit;

-- :name question :? :1
-- :doc Get all questions
select q.id qid, q.created_at qcreated_at, q.category qcategory, q.body qbody, u.display_name qauthor,
 a.id aid, a.created_at acreated_at, a.body abody, a.author aauthor from question q
left join answer a on q.id = a.question_id
join user u on q.user_id=u.id
where q.id = :id;

-- :name user-by-email :? :1
-- :doc get user by email
select * from "user" where email = :email;

-- :name insert-user :! :n
-- :doc Insert a user
insert into "user" (id, email, password, display_name, created_at)
values (:id, :email, :password, :display_name, CURRENT_TIMESTAMP);
