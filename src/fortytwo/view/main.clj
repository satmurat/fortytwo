(ns fortytwo.view.main
  (:use [hiccup.page :only (html5 include-css include-js)]
        [hiccup.element :only (link-to)]))

(defn not-found []
  [:div
   [:h1 {:class "info-warning"} "Page Not Found"]
   [:p "There's no requested page. "]
   (link-to {:class "btn btn-primary"} "/" "Take me to Home")])

(def login-form [:div#login-form [:form {:action "/user/login" :method "post" :onsubmit "main.core.login(this); return false"}
                                  [:input {:type "text" :name "email" :placeholder "email"}]
                                  [:input {:type "password" :name "password" :placeholder "password"}]
                                  [:input {:type "submit"}]]])

(defn login-corner [user-info]
  (if user-info
    [:a {:href "#"} (:display_name user-info)]
    ;(list [:a {:href "#" :onclick "$('#login-form').toggle()"} "Sign in"]
    (list [:a {:href "#" :onclick "main.core.toggle('#login-form')"} "Sign in"]
          login-form)))

(defn top-navbar [data]
  [:nav.navbar.navbar-inverse.navbar-fixed-top
   [:div.container-fluid
    [:div.navbar-header
     [:button.navbar-toggle.collapsed
      {:aria-controls "navbar",
       :aria-expanded "false",
       :data-target   "#navbar",
       :data-toggle   "collapse",
       :type          "button"}
      [:span.sr-only "Toggle navigation"]
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
     [:a.navbar-brand
      {:href "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
      "Forty two"]]
    [:div#navbar.navbar-collapse.collapse
     [:ul.nav.navbar-nav.navbar-right
      [:li
       (login-corner (:user-info data))]]
     [:form.navbar-form.navbar-right
      [:input.form-control
       {:placeholder "Search...", :type "text"}]]]]])

(def left-navbar
  [:div.col-sm-3.col-md-2.sidebar
   [:ul.nav.nav-sidebar
    [:li.active
     [:a
      {:href
       "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
      "Overview "
      [:span.sr-only "(current)"]]]]])

(defn application
  "data is {:top-navbar 'top nav bar' :left-navbar 'left nav bar' :content 'content'}"
  [title data]
  (html5
    [:head
     [:meta
      {:content "text/html; charset=UTF-8", :http-equiv "Content-Type"}]
     [:meta {:content "IE=edge", :http-equiv "X-UA-Compatible"}]
     [:meta
      {:content "width=device-width, initial-scale=1", :name "viewport"}]
     [:meta {:content "", :name "description"}]
     [:meta {:content "", :name "author"}]
     [:link
      {:href "http://getbootstrap.com/docs/3.3/favicon.ico",
       :rel  "icon"}]
     [:title title]
     [:link {:rel "stylesheet", :href "css/bootstrap.min.css"}]
     [:link
      {:rel "stylesheet", :href "css/ie10-viewport-bug-workaround.css"}]
     [:link {:rel "stylesheet", :href "css/dashboard.css"}]
     "<!--[if lt IE 9]><script src=\"js/ie8-responsive-file-warning.js\"></script><![endif]-->"
     [:script {:src "js/ie-emulation-modes-warning.js"}]
     "<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->"
     "<!--[if lt IE 9]>\n      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js\"></script>\n      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\n    <![endif]-->"]
    [:body
     (top-navbar data)
     [:div.container-fluid
      [:div.row
       left-navbar
       [:div.col-sm-9.col-sm-offset-3.col-md-10.col-md-offset-2.main
        "<!--"
        [:h1.page-header "Dashboard"]
        [:h2.sub-header "Section title"]
        "-->"
        [:h1.title title]
        [:div {:id "ok"} "no ok"]
        (:content data)]]]
     [:script {:src "js/jquery.min.js"}]
     [:script
      "window.jQuery || document.write('<script src=\"js/jquery.min.js\"><\\/script>')"]
     [:script {:src "js/bootstrap.min.js"}]
     "<!-- Just to make our placeholder images work. Don't actually copy the next line! -->"
     [:script {:src "js/holder.min.js"}]
     "<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->"
     [:script {:src "js/ie10-viewport-bug-workaround.js"}]

     [:script {:src "js/compiled/app.js"}]
     [:script "main.core.start()"]

     ]))