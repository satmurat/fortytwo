(ns fortytwo.layout.main
  (:use [hiccup.page :only (html5 include-css include-js)]))


(def header
  [:p "header"])

(def footer
  [:p "footer"])

(defn application [title & content]
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
         "Project name"]]
       [:div#navbar.navbar-collapse.collapse
        [:ul.nav.navbar-nav.navbar-right
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Dashboard"]]
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Settings"]]
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Profile"]]
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Help"]]]
        [:form.navbar-form.navbar-right
         [:input.form-control
          {:placeholder "Search...", :type "text"}]]]]]
     [:div.container-fluid
      [:div.row
       [:div.col-sm-3.col-md-2.sidebar
        [:ul.nav.nav-sidebar
         [:li.active
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Overview "
           [:span.sr-only "(current)"]]]
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Reports"]]
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Analytics"]]
         [:li
          [:a
           {:href
            "http://getbootstrap.com/docs/3.3/examples/dashboard/#"}
           "Export"]]]]
       [:div.col-sm-9.col-sm-offset-3.col-md-10.col-md-offset-2.main
        [:h1.page-header "Dashboard"]
        [:h2.sub-header "Section title"]
        content
        [:div.table-responsive
         [:table.table.table-striped
          [:thead
           [:tr
            [:th "#"]
            [:th "Header"]
            [:th "Header"]
            [:th "Header"]
            [:th "Header"]]]
          [:tbody
           [:tr
            [:td "1,001"]
            [:td "Lorem"]
            [:td "ipsum"]
            [:td "dolor"]
            [:td "sit"]]]]]]]]
     [:script {:src "js/jquery.min.js"}]
     [:script
      "window.jQuery || document.write('<script src=\"js/jquery.min.js\"><\\/script>')"]
     [:script {:src "js/bootstrap.min.js"}]
     "<!-- Just to make our placeholder images work. Don't actually copy the next line! -->"
     [:script {:src "js/holder.min.js"}]
     "<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->"
     [:script {:src "js/ie10-viewport-bug-workaround.js"}]])
  )