(ns soraw.core
  (:require [ring.adapter.jetty :as jetty]
            [soraw.handler :as handler]))

(defn -main [] (jetty/run-jetty
                 handler/app
                 {:port  3000
                  :join? false}))
