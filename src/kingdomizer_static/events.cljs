(ns kingdomizer-static.events
  (:require [re-frame.core]
            [kingdomizer-static.sets]
            [kingdomizer-static.util]
            [kingdomizer-static.db]
            [cljs.reader]
            [com.degel.re-frame.storage]
            [kingdomizer-static.randomization]))

(def sort-label->sort-vector {"set"  [:belongs-to-set :pile]
                              "name" [:pile-name :pile]
                              "cost" [:coins :cost :pile]})

(re-frame.core/reg-event-fx
 :persist-state-in-local-storage
 (fn [{:keys [db]} _]
   {:storage/set {:session? false
                  :name     :state
                  :value    (pr-str db)}}))

(re-frame.core/reg-event-fx
 :initialize-db
 [(re-frame.core/inject-cofx :storage/get {:name :state})]
 (fn [{db :db state :storage/get :as all}] ;; "state" is nil the first time the app is loaded
   {:db (merge db
               (if-let [edn-state (cljs.reader/read-string state)]
                 edn-state
                 {:sort-method              "set"
                  :selected-number-of-piles 10
                  :selected-sets            (->> kingdomizer-static.sets/all-dominion-sets
                                                 (map (fn [set-name]
                                                        [set-name true]))
                                                 (into {}))}))}))

(re-frame.core/reg-event-db
 :set-selected-sets
 (fn [db [_ selected-sets]]
   (assoc db :selected-sets selected-sets)))

(re-frame.core/reg-event-db
 :toggle-set-selection
 (fn [db [_ set-name]]
   (update-in db [:selected-sets set-name] not)))

(re-frame.core/reg-event-db
 :select-number-of-piles
 (fn [db [_ number-of-piles]]
   (assoc db :selected-number-of-piles number-of-piles)))

(defn cost-sorting-fn [{{{:keys [coins debt potions]} :cost} :pile}]
  (cond-> 0
    coins   (+ coins)
    debt    (+ debt)
    potions (+ (* 4 potions))))

(defn apply-sort-index [sort-method randomized-piles]
  (->> randomized-piles
       (sort-by (apply comp (get sort-label->sort-vector "name"))) ;; Always sort by name first
       (sort-by (fn sorting-fn [pile]
                  (if (= "cost" sort-method)
                    (cost-sorting-fn pile)
                    ((apply comp (get sort-label->sort-vector sort-method)) pile))))
       (map-indexed (fn [index pile]
                      (assoc-in pile [:display-data :sort-index] index)))))

(re-frame.core/reg-event-db
 :set-sort-method
 (fn [{:keys [randomized-piles] :as db} [_ sort-method]]
   (assoc db :randomized-piles (->> randomized-piles
                                    (apply-sort-index sort-method)
                                    set)
             :sort-method      sort-method)))

(re-frame.core/reg-event-db
 :set-randomized-piles
 (fn [db [_ randomized-piles]]
   (->> randomized-piles
        set
        (assoc db :randomized-piles))))

(re-frame.core/reg-event-db
 :initialize-randomized-piles
 (fn [{:keys [sort-method] :as db} [_ randomized-piles]]
   (->> randomized-piles
        (apply-sort-index sort-method)
        set
        (assoc db :randomized-piles))))

(re-frame.core/reg-event-db
 :set-error
 (fn [db [_ error-message]]
   (assoc db :error error-message)))

(re-frame.core/reg-event-db
 :swap-pile
 (fn [{:keys [randomized-piles] :as db} [_ renderable-pile]]
   (let [selected-sets-names (kingdomizer-static.db/only-selected-sets-names db)
         {:keys [error result] :as new-randomized-piles}
         (kingdomizer-static.randomization/swap-pile renderable-pile
                                              kingdomizer-static.sets/flattened-piles
                                              randomized-piles
                                              selected-sets-names)]
     (if error
       (assoc db :error error)
       (assoc db :randomized-piles result)))))

(re-frame.core/reg-event-db
 :randomize-set-selections
 (fn [db _]
   (->>  db
         kingdomizer-static.db/selected-sets
         (map (fn [[set-name selected?]]
                [set-name (rand-nth [true false])]))
         (into {})
         (assoc db :selected-sets))))

(re-frame.core/reg-event-db
 :set-all-sets-as-selected
 (fn [db _]
   (->>  db
         kingdomizer-static.db/selected-sets
         (map (fn [[set-name selected?]]
                [set-name true]))
         (into {})
         (assoc db :selected-sets))))

(re-frame.core/reg-event-db
 :set-all-sets-as-deselected
 (fn [db _]
   (->>  db
         kingdomizer-static.db/selected-sets
         (map (fn [[set-name selected?]]
                [set-name false]))
         (into {})
         (assoc db :selected-sets))))

(re-frame.core/reg-event-fx
 :generate-kingdom
 (fn [{:keys [db]} _]
   (let [sort-method                       (kingdomizer-static.db/sort-method db)
         {picked-piles                     :picked-piles
          events                           :events
          landmarks                        :landmarks
          {obelisk-pile-name :pile-name}   :obelisk-pile
          platinum                         :platinum
          colony                           :colony
          {bane-card-pile-name :pile-name} :bane-card-pile
          :as                              instructions} (kingdomizer-static.randomization/make-kingdom {:selected-sets            (kingdomizer-static.db/only-selected-sets-names db)
                                                                                                  :selected-number-of-piles @(re-frame.core/subscribe [:selected-number-of-piles])
                                                                                                  :full-pile-pool          kingdomizer-static.sets/flattened-piles
                                                                                                  :picked-piles             []})
         renderable-piles                                (->> picked-piles
                                                              (map (partial assoc {} :pile))
                                                              (map (fn prepare-bane-card-pile [{{:keys [pile-name]} :pile :as randomized-pile}]
                                                                     (cond-> randomized-pile
                                                                       (= pile-name bane-card-pile-name) (assoc-in [:display-data :bane-card-pile] true))))
                                                              (map (fn prepare-obelisk-card-pile [{{:keys [pile-name]} :pile :as randomized-pile}]
                                                                     (cond-> randomized-pile
                                                                       (= pile-name obelisk-pile-name) (assoc-in [:display-data :obelisk-card-pile] true))))
                                                              (apply-sort-index sort-method)
                                                              set)]
     {:db       (merge db {:randomized-piles renderable-piles
                           :events           events
                           :landmarks        landmarks
                           :platinum         platinum
                           :colony           colony})
      :dispatch [:persist-state-in-local-storage nil]})))
