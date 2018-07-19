(ns kingdomizer-static.prod
  (:require
    [kingdomizer-static.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
