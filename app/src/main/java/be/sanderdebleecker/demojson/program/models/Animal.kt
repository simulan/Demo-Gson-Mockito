package be.sanderdebleecker.demojson.program.models

/**
 * @author Simulan
 * @version 1.0.0
 * @since 25/06/2017
 */
abstract class Animal {
    abstract var friendly: Boolean
    abstract var speedInMPH : Int
    abstract var sound : String

    abstract fun makeNoise()
}