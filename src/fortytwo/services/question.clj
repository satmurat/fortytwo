(ns fortytwo.services.question
  (:require [fortytwo.services.db :as db]))

(defn qlist[] (db/all-questions db/db {:limit 10 }))
