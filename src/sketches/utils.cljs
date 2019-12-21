(ns sketches.perlin-utils
  (:require [quil.core :as q]
            
            [sketches.perlin-const :as c]))


(defn particle
  "Creates a particle map."
  [id]
  {:id         id
   :vx         1
   :vy         1
   :size       3
   :direction  0
   :x          (q/random c/w)
   :y          (q/random c/h)
   :color      (rand-nth (:colors c/palette))})

(defn position
  "Calculates the next position based on the current, the speed and a max."
  [current delta max]
  (mod (+ current delta) max))

(defn velocity
  "Calculates the next velocity by averaging the current velocity and the added delta."
  [current delta]
  (/ (+ current delta) 2))

(def noise-zoom
  "Noise zoom level."
  0.007)

(defn direction
  "Calculates the next direction based on the previous position and id of each particle."
  [x y z]
  (* 2
     Math/PI
     (+ (q/noise (* x noise-zoom) (* y noise-zoom))
        (* 0.4 (q/noise (* x noise-zoom) (* y noise-zoom) (* z noise-zoom))))))
