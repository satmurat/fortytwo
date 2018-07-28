(ns fortytwo.routes.auth
  (:require [compojure.core :refer [GET POST defroutes context]]
            [clojure.string :as str]
            [ring.util.response :refer [response]]
            [buddy.auth.accessrules :refer [restrict error]]
            [buddy.auth.backends.token :refer [jws-backend]]
            [buddy.hashers :as hashers]
            [buddy.sign.jwt :as jwt]
            [clj-time.core :as time]
            [config.core :refer [env]]
            [fortytwo.services.db :as db]
            [fortytwo.helper :as h]))

(def secret (:token-secret env))

(def token-name (:token-name env))

(def sec-options {:alg :hs512})

(def token-lifetime-sec 3600)

(defn user-cookie-info [req]
  (if-let [token-signed (get-in req [:cookies token-name :value])]
    (jwt/unsign token-signed secret sec-options)))

(defn user-db-info [req]
  (if-let [u (user-cookie-info req)]
    (db/user-by-email db/db {:email (:user u)})))

(defn roles [req]
  (if-let [user-info (user-cookie-info req)]
    (apply hash-set (:roles user-info))
    ["anonymous"]))

(defn has-role? [req role]
  (h/in? (roles req) role))

(defn moderator? [req]
  (has-role? req "moderator"))

(defn anonymous? [req]
  (has-role? req "anonymous"))

(defn logged? [req]
  (not (anonymous? req)))

(defn restrict-resp [body rules] (restrict (fn [req] (response body)) rules))

(defn on-error [request value]
  (let [is-anonymous (anonymous? request)
        status (if is-anonymous 401 403)
        body (if is-anonymous "Unauthorized" "Forbidden")]
    {:status  status
     :headers {"Content-type" "text/plain"}
     :body    body}))

(defn lookup-user
  "returns user row from db or nil, if not found"
  [email password]
  (if-let [user (db/user-by-email db/db {:email email})]
    (if (hashers/check password (:password user))
      (dissoc user :password))))                            ; Strip out user password

(defn login [request]
  (prn (:body request))
  (let [{{:keys [email password]} :body} request
        user (lookup-user email password)]
    (if user
      (let [claims {:user  email
                    :roles (str/split (:roles user) #",")
                    :exp   (time/plus (time/now) (time/seconds token-lifetime-sec))}
            token (jwt/sign claims secret sec-options)]
        {:status  200
         :body    token
         :headers {"Content-Type" "text/plain"}
         :cookies {token-name {:value token, :path "/", :expires (:exp claims)}}})
      {:status  400
       :body    "Wrong login or password"
       :headers {"Content-type" "text/plain"}})))

(defn logout [req]
  {:status  204
   :headers {"Content-Type" "text/plain"}
   :cookies {token-name {:value "", :path "/", :max-age 0}}})

(defn password-validation [pw pw-verify]
  (cond
    (< (count pw) 8) "Password min length must be 8"
    (not= pw pw-verify) "Password and verify must be equal"))

(defn name-validation [name]
  (cond
    (or (< (count name) 3) (> (count name) 30)) "Display name length must be in range 3 and 30"))

(defn email-validation [email]
  (cond
    (str/blank? email) "Email must be not empty"
    (not (re-matches #".+@.+\..+" email)) "Not valid email"
    (db/user-by-email db/db {:email email}) "Email already exists"))

(defn validate-user-data [body]
  (let [{:keys [email password password-verify display-name]} body
        pw-error (password-validation password password-verify)
        email-error (email-validation email)
        name-error (name-validation display-name)
        errors [pw-error email-error name-error]]
    (remove nil? errors)))

(defn register [req]
  (let [body (:body req)
        {:keys [email password display-name]} body
        errors (validate-user-data body)]
    (if (not-empty errors) {:status 400 :headers {} :body {:error errors}}
                           (do
                             (db/insert-user db/db {:id           (java.util.UUID/randomUUID)
                                                    :email        email
                                                    :password     (hashers/derive password)
                                                    :display_name display-name})
                             {:status 204 :headers {}}
                             ))))

(defroutes auth-routes
           (context "/user" []
             (POST "/login" [] (restrict login {:handler  anonymous?
                                                :on-error on-error}))
             (POST "/register" [] (restrict register {:handler  anonymous?
                                                      :on-error on-error}))
             (GET "/logout" [] (restrict logout {:handler  logged?
                                                 :on-error on-error}))))