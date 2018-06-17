(ns fortytwo.routes.home
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [fortytwo.view.main :as main-view]
            [fortytwo.view.question :as q-view]
            [fortytwo.services.question :as q-service]
            [ring.util.response :refer [response]]
            [buddy.auth.accessrules :refer [restrict]]
            [fortytwo.routes.auth :refer :all]))

(defroutes home-routes
           (route/resources "/")

           (GET "/" [] (q-view/list-on-main (q-service/qlist)))

           (GET "/json" [] (restrict-resp (q-service/qlist) {:handler  logged?
                                                             :on-error on-error}))

           (route/not-found (main-view/application "Oops..." {:content (main-view/not-found)})))
