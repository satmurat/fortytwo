(ns fortytwo.services.migrations
  (:require [migratus.core :as migratus]
            [fortytwo.services.db :as db]))

(def config {:store         :database
             :migration-dir "migrations/"
             :init-script   "init.sql"
             :db            db/db})