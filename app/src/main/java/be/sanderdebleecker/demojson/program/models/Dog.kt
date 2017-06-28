package be.sanderdebleecker.demojson.program.models

/**
 * @author Simulan
 * @version 1.0.0
 * @since 25/06/2017
 */

class Dog (override var friendly: Boolean = true,
               override var speedInMPH: Int = 50,
               override var sound: String = "Woof",
               var legs: Int = 5) : Animal() {

    override fun makeNoise() {
        println(sound)
    }
}