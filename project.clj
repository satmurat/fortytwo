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
                 [buddy/buddy-auth "2.1.0"]
                 [buddy/buddy-hashers "1.3.0"]
                 [ring/ring-json "0.4.0"]
                 [yogthos/config "1.1.1"]
                 [compojure "1.5.1"]
                 [hikari-cp "2.4.0"]
                 [migratus "1.0.6"]
                 [hiccup "1.0.5"]
                 [ring "1.4.0"]]
  ;:plugins [[lein-ring "0.9.7"]]
  ;:ring {:handler fortytwo.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [org.clojure/tools.nrepl "0.2.13"]
                        [ring/ring-mock "0.3.0"]]
         :resource-paths ["env/dev/resources"]}}
  :main fortytwo.core)
