(ns fortytwo.handler
  (:require [compojure.core :refer [routes]]
            [fortytwo.routes.home :as home]
            [fortytwo.routes.auth :as auth]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as json-middleware]))

(def app
  (->
    (wrap-defaults
      (routes #'auth/auth-routes #'home/home-routes)
      (assoc-in site-defaults [:security :anti-forgery] false))
    wrap-reload
    json-middleware/wrap-json-response
    (json-middleware/wrap-json-body {:keywords? true})))
