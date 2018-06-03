(ns fortytwo.services.db
  (:require [hikari-cp.core :refer :all]
            [fortytwo.config :as config]
            [hugsql.core :as hugsql]))

(def datasource-options {:jdbc-url config/jdbc-url})

(def db
  {:datasource (make-datasource datasource-options)})

(hugsql/def-db-fns "sql/queries.sql")