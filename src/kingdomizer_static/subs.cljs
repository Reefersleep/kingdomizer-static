(ns kingdomizer-static.subs
  (:require [re-frame.core]
            [kingdomizer-static.db]
            [kingdomizer-static.util]))

(re-frame.core/reg-sub
 :error
 (fn [db _]
   (:error db)))

(re-frame.core/reg-sub
 :selected-sets
 (fn [db _]
   (:selected-sets db)))

(re-frame.core/reg-sub
 :only-selected-sets-names
 (fn [db _]
   (kingdomizer-static.db/only-selected-sets-names db)))

(re-frame.core/reg-sub
 :selected-set?
 (fn [db [_ set-name]]
   (get-in db [:selected-sets set-name])))

(re-frame.core/reg-sub
 :no-sets-selected?
 :<- [:selected-sets]
 (fn [selected-sets _]
   (->> selected-sets
        vals
        (filter true?)
        empty?)))

(re-frame.core/reg-sub
 :sort-method
 (fn [db _]
   (:sort-method db)))

(re-frame.core/reg-sub
 :selected-number-of-piles
 (fn [db _]
   (-> db :selected-number-of-piles)))

(re-frame.core/reg-sub
 :randomized-piles
 (fn [{:keys [randomized-piles] :as db} _]
   (->> randomized-piles
        (sort-by (comp :sort-index :display-data)))))

(re-frame.core/reg-sub
 :events
 (fn [db _]
   (:events db)))

(re-frame.core/reg-sub
 :landmarks
 (fn [db _]
   (:landmarks db)))

(re-frame.core/reg-sub
 :platinum
 (fn [db _]
   (:platinum db)))

(re-frame.core/reg-sub
 :colony
 (fn [db _]
   (:colony db)))
