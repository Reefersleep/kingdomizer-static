# Kingdomizer
## A Dominion randomizer

You can try Kingdomizer here: http://kingdomizer.herokuapp.com

Kingdomizer is beta! Expect questionable features and breaking changes.

The app might take a few seconds to spin up since it's hosted for free on Heroku.

To run tests; `lein doo nodejs test` (you need to have nodejs installed)


Development roadmap:


- [x] _functionality_ User can pick available sets 
- [x] _functionality_ User can clear selected sets at once
- [x] _functionality_ User can select all sets at once
- [x] _functionality_ User can have Kingdomizer pick _n_ cards automatically from available sets
- [x] _design_ Simple "less-is-more" display of cards" - a) just the name and set
- [x] _design_ Simple "less-is-more" display of cards" - b) using Trajan Pro, the font used on the real Dominion cards - available from http://fontsforweb.com/font/show?id=13972
- [x] _design_ Beautiful display of card types
- [x] _design_ Beautiful display of set names for cards
- [x] _functionality_ User can have Kingdomizer pick random sets (from all Dominion sets)
- [x] _robustness_ Simplify algorithm design so that it does not use the overengineered log design - it's currently hampering progress by being more complicated than necessary, and because I cannot envision how it should fully work 
- [x] _design_ Set icons
- [x] _design_ Beautiful display of card cost (coins only)
- [x] _design_ Beautiful display of card cost (debt)
- [x] _design_ Beautiful display of card cost (potions)
- [x] _functionality_ Show error message on inability to pick bane card
- [x] _functionality_ Add option to swap selected pile
- [x] _functionality_ Show error message on inability to swap pile
- [x] _design_ Beautiful display of swap button
- [x] _robustness_ Use [re-frame][https://github.com/Day8/re-frame] to structure the frontend application
- [x] _functionality_ ~~Keep list order on pile swap in order to reduce confusion. Swap upon next sort selection~~ _deprecated_
- [x] _design_ Make it so that sorting only happens when actually choosing sort style, and not reactively. This way, the UI won't be so confusing when swapping a pile. Currently, the reactive order of piles means that potentially, the whole list jumps, which is a really bad user experience.
- [x] _functionality_ Have Kingdomizer automatically pick (and display) 0-2 Events when using Adventures
- [x] _functionality_ Have Kingdomizer automatically pick (and display) Platinum and Colonies if the first picked pile is from Prosperity
- [x] _functionality_ Have Kingdomizer automatically pick (and display) 0-2 Events/Landmarks (mixed, 0-2 total) when using Empires (or Empires _and_ Adventures)
- [x] _functionality_ Have Kingdomizer automatically pick and display a pile as the Obelisk pile when having picked the Obelisk Landmark
- [x] _functionality_ Add Nocturne set
- [x] _functionality_ Ensure at least 3 piles from Alchemy when it is chosen
- [x] _functionality_ The user can, upon revisiting Kingdomizer, view the kingdom which was created last before closing the browser
- [ ] _robustness_ Build a proper taxonomy for Dominion concepts; I find that names such as "sets", ":non-supply", "pick" and others are a bit ambiguous or just imprecise. 
- [ ] _robustness_ Describe the mechanics of Kingdomizer from a high level perspective using a well-thought out taxonomy.
- [ ] _functionality_ Have Kingdomizer automatically pick (and display) Potions when a potion-costing pile has been picked
- [ ] _robustness_ Refactor smaller components from core structure, for code clarity
- [ ] _robustness_ Refine re-frame subscriptions and events granularity
- [ ] _robustness_ Refine re-frame keyword naming
- [ ] _robustness_ Refine re-frame state structure
- [ ] _robustness_ Find more elegant, simple way to note the selected bane-card
- [ ] _design_ More discrete display of swap button, maybe with mouse-over highlight?
- [ ] _design_ Beautiful display of error message on inability to pick bane card
- [ ] _design_ Beautiful display of card cost (coins including *)
- [ ] _functionality_ User can save generated sets in localStorage
- [ ] _functionality_ User can view earlier generated sets
- [ ] _functionality_ User can mark generated set as actually used rather than ignored
- [ ] _functionality_ User can mark generated setwhich was previously marked as actually used as not used 
- [ ] _functionality_ Upon user marking generated set as used, prompt for names of winners and losers
- [ ] _functionality_ Upon user typing in name, persist name as player for potential use in future prompts
- [ ] _functionality_ Upon winner/loser prompt, make player names available for choice, so as to make statistics across games possible with repeat players, and so as to avoid re-typing names
- [ ] _functionality_ User can perform textual search for cards with completion
- [ ] _functionality_ User can switch each card of a generated set manually with a different one
- [ ] _robustness_ Unit tests for core functionality
- [ ] _robustness_ Use [devcards][https://github.com/bhauman/devcards] to more effectively preview and regression test UI components *EDIT: I will postpone this, as devcards has an error on master; https://github.com/bhauman/devcards/pull/123 . I attempted to introduce devcards to do this project, but after introducing the devcards dependency, I experienced the defect referenced in the link, as I got errors in the browser console stating: 

Uncaught Error: js/ReactDOM is missing

... 

(lots of lines omitted)

...

at reagent$core$render (core.cljs?rel=1510437342082:66)

at Function.kingdomizer$core$mount_root (core.cljs?rel=1510521817189:354)"*
