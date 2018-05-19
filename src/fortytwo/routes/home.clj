(ns fortytwo.routes.home
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [fortytwo.layout.main :as layout]
            [fortytwo.layout.contents :as contents]
            [ring.util.response :refer [response]]))

(defroutes home-routes
           (route/resources "/")
           (GET "/" [] (layout/application "Home" (contents/index)))
           (GET "/get" [] (response {:name "sat"}))
           (route/not-found (layout/application "Home" (contents/not-found))))
