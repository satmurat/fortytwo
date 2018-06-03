(ns fortytwo.routes.home
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [fortytwo.view.main :as main-view]
            [fortytwo.view.question :as q-view]
            [fortytwo.services.question :as q-service]
            [ring.util.response :refer [response]]))

(defroutes home-routes
           (route/resources "/")

           (GET "/" [] (q-view/list-on-main (q-service/qlist)))

           (GET "/json" [] (response (q-service/qlist)))

           (route/not-found (main-view/application "Oops..." {:content (main-view/not-found)})))
