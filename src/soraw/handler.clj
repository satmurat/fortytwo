(ns soraw.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [soraw.routes.home :as homeroutes]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as json-middleware]))

;(defroutes app-routes
;  (GET "/" [] "Hello World")
;  (route/not-found "Not Found"))

(def app
  (->
    (wrap-defaults
      (json-middleware/wrap-json-response #'homeroutes/home-routes)
      site-defaults)
    wrap-reload
    (json-middleware/wrap-json-body {:keywords? true})
    ))
