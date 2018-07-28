(ns main.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure.browser.event :as event]
            ))

(defn ok[] [:div "ok"])

(def jq (js* "$"))

(defn toggle [id]
  (.toggle (jq id)))

(defn login [this]
  (js/alert "1")
  )

(defn ^:export start []
  (reagent/render-component [ok] (.getElementById js/document "ok")))