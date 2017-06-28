package be.sanderdebleecker.demojson.program.models

/**
 * @author Simulan
 * @version 1.0.0
 * @since 25/06/2017
 */
 class Cat(    override var friendly: Boolean = true,
                   override var speedInMPH: Int = 40,
                   override var sound: String = "miaw",
                   var canClimb: Boolean = true) : Animal() {


    override fun makeNoise() {
        println(sound)
    }
}