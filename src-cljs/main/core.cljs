(ns main.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.browser.event :as event]
            [ajax.core :refer [POST]]))

(defn ok[] [:div "ok"])

(def jq (js* "$"))

(defn toggle [id]
  (.toggle (jq id)))

(defn form-val-by-name [form elem]
  (-> (.-elements form)
      (.namedItem elem)
      .-value))

(defn login [this]
  (let [email (form-val-by-name this "email")
        password (form-val-by-name this "password")
        data {:email email :password password}]
    (POST "/user/login"
          {:format        :json
           :params        data
           :handler       (fn [_] (.reload js/window.location))
           :error-handler (fn [e] (.html (jq "#message") (:response e)))})))



(defn ^:export start []
  (reagent/render-component [ok] (.getElementById js/document "ok")))