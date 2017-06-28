package be.sanderdebleecker.demojson.program

import be.sanderdebleecker.demojson.program.type_adapters.NamesArrayDeserializer
import be.sanderdebleecker.demojson.program.type_adapters.NamesInHaystackDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader

/**
 * @author Simulan
 * @version 1.0.0
 * @since 22/06/2017
 */
open class Deserializer() {

    //Simplistic implementations
    /**
     * This is safe against JSON array hijacking but clumsy
     */
    fun namesAsObject(input : InputStream?): NamesObject {
        val gson: Gson = Gson()
        if (input != null) {
            val reader: Reader = InputStreamReader(input)
            return gson.fromJson(reader, NamesObject::class.java)
        } else {
            return NamesObject()
        }
    }
    /**
     * This is efficient but unsafe against JSON hijacking
     */
    fun namesAsArray(input : InputStream?): Array<String> {
        val gson: Gson = Gson()
        if (input != null) {
            val reader: Reader = InputStreamReader(input)
            return gson.fromJson(reader, Array<String>::class.java)
        } else {
            return arrayOf()
        }
    }
    /**
     * This is efficient and safe
     */
    fun  namesAsEncapsulatedArray(input: InputStream?): Array<String> {
        val gsonBuilder : GsonBuilder = GsonBuilder().registerTypeAdapter(Array<String>::class.java,NamesArrayDeserializer())
        val gson2 = gsonBuilder.create()
        if (input != null) {
            val reader: Reader = InputStreamReader(input)
            return gson2.fromJson(reader, Array<String>::class.java)
        } else {
            return arrayOf()
        }
    }

    //API client implementations
    /**
     * Finds the name jsonArray in a nested structure
     */
    fun namesInHaystack(input: InputStream?): Array<String> {
        val gsonBuilder = GsonBuilder().registerTypeAdapter(Array<String>::class.java,NamesInHaystackDeserializer())
        val gson2 = gsonBuilder.create()
        if(input!=null) {
            val reader: Reader = InputStreamReader(input)
            return gson2.fromJson(reader, Array<String>::class.java)
        }else{
            return arrayOf()
        }
    }
    /**
     * Registering two typeadapters for the same type
     */
    fun  namesByTwoTypeAdapters(input: InputStream): Array<String> {
        val gsonBuilder = GsonBuilder()
                .registerTypeAdapter(Array<String>::class.java, NamesArrayDeserializer())
                .registerTypeAdapter(Array<String>::class.java, NamesInHaystackDeserializer())
        val gson2 = gsonBuilder.create()
        if(input!=null) {
            val reader: Reader = InputStreamReader(input)
            return gson2.fromJson(reader, Array<String>::class.java)
        }else{
            return arrayOf()
        }
    }

    fun testFun() : Int {
        return 5
    }
}