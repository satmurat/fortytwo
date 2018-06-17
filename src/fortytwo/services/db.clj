(ns fortytwo.services.db
  (:require [hikari-cp.core :refer :all]
            [config.core :refer [env]]
            [hugsql.core :as hugsql]))

(def datasource-options {:jdbc-url (:database-url env)})

(def db
  {:datasource (make-datasource datasource-options)})

(hugsql/def-db-fns "sql/queries.sql")