(ns luminous-clj.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [luminous-clj.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[luminous-clj started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[luminous-clj has shut down successfully]=-"))
   :middleware wrap-dev})
