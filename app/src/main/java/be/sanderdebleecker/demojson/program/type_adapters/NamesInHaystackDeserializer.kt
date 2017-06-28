package be.sanderdebleecker.demojson.program.type_adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * @author Simulan
 * @version 1.0.0
 * @since 25/06/2017
 */
class NamesInHaystackDeserializer : JsonDeserializer<Array<String>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Array<String> {
        val l1dataObject = (json as JsonObject).getAsJsonObject("data")
        val l2dataObject = l1dataObject.getAsJsonObject("data")
        val l3ListObject = l2dataObject.getAsJsonObject("list")
        val l4NamesArray = l3ListObject.getAsJsonArray("names")
        return Array(l4NamesArray.size(), { i -> l4NamesArray[i].asString })
    }
}