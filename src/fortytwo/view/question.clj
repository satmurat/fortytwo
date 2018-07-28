(ns fortytwo.view.question
  (:require [fortytwo.view.main :as main-view]
            [fortytwo.helper :as helper]))

(defn question-html [q]
   [:p
    [:strong (:qtitle q)] [:br] [:i "@"(:qauthor q)]" in " [:i(:qcategory q)]
    " at " (helper/sql-time-str (:qcreated_at q))
    ". Answers: " (:answers_count q)])


(defn list-on-main [questions user-info]
  (main-view/application "Last questions"
                         {:content (map question-html questions)
                          :user-info user-info}))

