(ns fortytwo.core
  (:require [ring.adapter.jetty :as jetty]
            [fortytwo.handler :as handler]))

(defn -main [] (jetty/run-jetty
                 handler/app
                 {:port  3000
                  :join? false}))
