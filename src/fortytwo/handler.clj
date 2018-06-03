(ns fortytwo.handler
  (:require [compojure.core :refer :all]
            [fortytwo.routes.home :as homeroutes]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as json-middleware]))

(def app
  (->
    (wrap-defaults
      (json-middleware/wrap-json-response #'homeroutes/home-routes)
      site-defaults)
    wrap-reload
    (json-middleware/wrap-json-body {:keywords? true})))
