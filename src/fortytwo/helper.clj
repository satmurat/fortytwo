(ns fortytwo.helper
  (:require [clj-time.coerce :as coerce]
            [clj-time.format :as f]))

(defn sql-time-str
  ([t format]
  (f/unparse (f/formatter format) (coerce/from-sql-time t)))

  ([t]
   (sql-time-str t "dd.MM.YYYY HH:mm:ss")))

(defn in?
  "true if coll contains elm"
  [coll elm]
  (some #(= elm %) coll))