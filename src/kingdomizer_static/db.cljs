(ns kingdomizer-static.db)

(defn only-selected-sets-names [db]
  (->> db
       :selected-sets
       (filter (fn [[set-name selected?]]
                 selected?))
       (map first)
       set))

(defn only-selected-promo-names [db]
  (->> db
       :selected-promos
       (filter (fn [[promo-name selected?]]
                 selected?))
       (map first)
       set))

(defn selected-sets [db]
  (:selected-sets db))

(defn sort-method [db]
  (:sort-method db))
