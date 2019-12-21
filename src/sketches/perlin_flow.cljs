(ns sketches.perlin-flow
  (:require [quil.core :as q]
            [quil.middleware :as middleware]
            
            [sketches.perlin-utils :as u]
            [sketches.perlin-const :as c]))


(defn sketch-setup
  "Returns the initial state to use for the update-render loop."
  []
  (map u/particle (range 0 2000)))


(defn sketch-update
  "Returns the next state to render. Receives the current state as a paramter."
  [particles]
  (map (fn [p]
         (assoc p
                :x         (u/position (:x p) (:vx p) c/w)
                :y         (u/position (:y p) (:vy p) c/h)
                :direction (u/direction (:x p) (:y p) (:id p))
                :vx        (u/velocity (:vx p) (Math/cos (:direction p)))
                :vy        (u/velocity (:vy p) (Math/sin (:direction p)))))
       particles))

(defn sketch-draw
  "Draws the current state to the canvas. Called on each iteration after sketch-update."
  [particles]
  ; (apply q/background (:background palette))
  (q/no-stroke)
  (doseq [p particles]
    (apply q/fill (conj (:color p) 4))
    (q/ellipse (:x p) (:y p) (:size p) (:size p))))


(defn create [canvas]
  (q/sketch
   :host canvas
   :size [c/w c/h]
   :draw #'sketch-draw
   :setup #'sketch-setup
   :update #'sketch-update
   :middleware [middleware/fun-mode]
   :settings (fn []
               (q/random-seed 666)
               (q/noise-seed 666))))

(defonce sketch (create "sketch"))
