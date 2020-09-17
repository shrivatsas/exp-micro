(ns luminous-clj.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[luminous-clj started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[luminous-clj has shut down successfully]=-"))
   :middleware identity})
