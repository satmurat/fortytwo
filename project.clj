(defproject fortytwo "0.1.0-SNAPSHOT"
  :description "Clojure implementation of question-answer platform"
  :url "https://fortytwo.io"
  :min-lein-version "2.0.0"
  :dependencies [[ch.qos.logback/logback-classic "1.2.3"]
                 [org.postgresql/postgresql "42.2.2"]
                 [org.clojure/java.jdbc "0.7.6"]
                 [com.layerware/hugsql "0.4.8"]
                 [org.clojure/clojure "1.9.0"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.5.1"]
                 [hikari-cp "2.4.0"]
                 [migratus "1.0.6"]
                 [hiccup "1.0.5"]
                 [ring "1.4.0"]]
  ;:plugins [[lein-ring "0.9.7"]]
  ;:ring {:handler fortytwo.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}}
  :main fortytwo.core)
