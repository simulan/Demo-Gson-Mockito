package be.sanderdebleecker.demojson.program.type_adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * @author Simulan
 * @version 1.0.0
 * @since 24/06/2017
 */

class NamesArrayDeserializer : JsonDeserializer<Array<String>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<String> {
        val jArray = (json as JsonObject).getAsJsonArray("names")
        return Array(jArray.size(), { i -> jArray[i].asString })
    }
}