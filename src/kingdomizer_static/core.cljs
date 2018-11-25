(ns kingdomizer-static.core
  (:require [reagent.core]
            [re-frame.core]
            [goog.dom]
            [kingdomizer-static.sets]
            [kingdomizer-static.events]
            [kingdomizer-static.subs]
            [kingdomizer-static.util]
            [kingdomizer-static.components]))

(defn add-react-keys
  "Assigns unique 'key' props to the 'components' seq elements.
  React gives a 'warning' if each element of a seq does not have a unique 'key' prop. "
  [components]
  (map (fn [component]
         (with-meta component {:key (random-uuid)}))
       components))

(defn get-value
  [node]
  (-> node
      .-target
      .-value))

(defn capitalize-words
  "Capitalize every word in a string
  Shamelessly stolen from https://clojuredocs.org/clojure.string/capitalize"
  [s]
  (->> (clojure.string/split s #"\b")
       (map clojure.string/capitalize)
       clojure.string/join))

(defn kw->label [kw]
  (-> kw
      name
      (clojure.string/replace #"-" " ")
      capitalize-words))

(def card-banner-background-edge  "rgb(112, 129, 126)")
(def dominion-box-rim "rgb(144, 136, 117)")
(def card-textfield-background "rgb(175, 176, 170)")
(def events-banner-background "rgb(157, 171, 156)")
(def landmarks-banner-background "rgb(78, 168, 104)")

(defn set-name->set-icon [set-name]
  (-> set-name
      .toLowerCase
      (clojure.string/replace #" " "_")
      (str "_set_icon.png")))

(defn body-column [& components]
  (into [kingdomizer-static.components/body-column] components))

(defn title-banner []
  [kingdomizer-static.components/title-banner])

(defn control-panel [& components]
  (into [kingdomizer-static.components/control-panel] components))

(defn pile-number-selection []
  [:div {:style {:display         :flex
                 :justify-content :flex-end}}
   "Desired number of piles: "
   [:input {:type      :number
            :min       1
            :max       20
            :value     @(re-frame.core/subscribe [:selected-number-of-piles])
            :on-change (fn toggle-select-number-of-piles [node]
                         (let [number-of-piles (-> node
                                                   get-value
                                                   int)]
                           (re-frame.core/dispatch [:select-number-of-piles number-of-piles])))}]])

(defn control-panel-bottom-row []
  [kingdomizer-static.components/control-panel-bottom-row {:clear-sets #(re-frame.core/dispatch [:set-all-sets-as-deselected])
                                                           :select-all-sets #(re-frame.core/dispatch [:set-all-sets-as-selected])
                                                           :select-random-sets #(re-frame.core/dispatch [:randomize-set-selections])}])

(defn sets-selection []
  [kingdomizer-static.components/sets-selection {:set-selected?        (fn [set-name]
                                                                         @(re-frame.core/subscribe [:selected-set? set-name]))
                                                 :toggle-set-selection (fn [set-name]
                                                                         (re-frame.core/dispatch [:toggle-set-selection set-name]))}])

(defn promos-selection []
  [kingdomizer-static.components/promos-selection {:selected-promo?        (fn [promo-name]
                                                                             @(re-frame.core/subscribe [:selected-promo? promo-name]))
                                                   :toggle-promo-selection (fn [promo-name]
                                                                             (re-frame.core/dispatch [:toggle-promo-selection promo-name]))}])

(defn error-reporting []
  [kingdomizer-static.components/error-reporting])

(defn generated-kingdom []
  [kingdomizer-static.components/generated-kingdom {:randomized-piles #(@(re-frame.core/subscribe [:randomized-piles]))
                                                    :platinum? #(@(re-frame.core/subscribe [:platinum]))
                                                    :colony?   #(@(re-frame.core/subscribe [:colony]))
                                                    :get-events #(@(re-frame.core/subscribe [:events]))}])

(defn new-kingdom-trigger []
  [kingdomizer-static.components/new-kingdom-trigger {:generate-new-kingdom #(re-frame.core/dispatch [:generate-kingdom])
                                                      :disabled?            #(@(re-frame.core/subscribe [:no-sets-selected?]))}])

(defn pile-sort-selection []
  [kingdomizer-static.components/pile-sort-selection {:show?          #(seq @(re-frame.core/subscribe [:randomized-piles]))
                                                      :on-sort-change (fn [node]
                                                                        (re-frame.core/dispatch [:set-sort-method (get-value node)]))}])

(defn home-page []
  [body-column
   [title-banner]
   [control-panel
    [control-panel-bottom-row]]
   [sets-selection]
   #_[pile-number-selection] ;; Maybe only for advanced instructions
   [promos-selection]
   [new-kingdom-trigger]
   [pile-sort-selection]
   [error-reporting]
   [generated-kingdom]])

;; -------------------------
;; Initialize app

(defn mount-root []
  (re-frame.core/dispatch-sync [:initialize-db])
  (reagent.core/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
