(ns luminous-clj.routes.home
  (:require
   [luminous-clj.layout :as layout]
   [cheshire.core :as json]
   [luminous-clj.db.core :as db]
   [clojure.java.io :as io]
   [luminous-clj.middleware :as middleware]
   [ring.util.response]
   [ring.util.http-response :as response]
   [clj-http.client :as client]))

(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn fetch-time [request]
  {
    :status 200
    :headers {"Content-Type" "text/plain"}
    :body ((json/decode (:body (client/get (str "http://worldtimeapi.org/api/timezone/" "America/Curacao")))) "datetime")
  })
  
(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/random" {:get fetch-time}]
   ["/about" {:get about-page}]])

