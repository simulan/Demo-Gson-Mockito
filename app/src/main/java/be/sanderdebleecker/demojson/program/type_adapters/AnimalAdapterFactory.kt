package be.sanderdebleecker.demojson.program.type_adapters

import be.sanderdebleecker.demojson.program.models.Animal
import be.sanderdebleecker.demojson.program.models.Cat
import be.sanderdebleecker.demojson.program.models.Dog
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

/**
 * @author Simulan
 * @version 1.0.0
 * @since 26/06/2017
 */

class AnimalAdapterFactory : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
        if (!Animal::class.java.isAssignableFrom(type!!.rawType)) {
            return null
        } else {
            return AnimalAdapter<T>(type!!.rawType.simpleName) as TypeAdapter<T>
        }
    }

    inner class AnimalAdapter<T : Any?>(val type: String) : TypeAdapter<Animal>() {
        override fun write(out: JsonWriter?, value: Animal?) {
            if (out != null && value != null) {
                out.beginObject()
                out.name("friendly").value(value.friendly)
                out.name("sound").value(value.sound)
                out.name("speedInMPH").value(value.speedInMPH)
                when (type) {
                    "Cat" -> {
                        val cat = value as Cat
                        out.name("canClimb").value(cat.canClimb)
                    }
                    "Dog" -> {
                        val dog = value as Dog
                        out.name("legs").value(dog.legs)
                    }
                }
                out.endObject()
            }
        }
        override fun read(reader: JsonReader?): Animal {
            when (type) {
                "Cat" -> return readCat(reader!!)
                else -> return readDog(reader!!)
            }
        }
        fun readDog(reader: JsonReader): Dog {
            var dog = Dog()
            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "friendly" -> dog.friendly = reader.nextBoolean()
                    "sound" -> dog.sound = reader.nextString()
                    "speedInMPH" -> dog.speedInMPH = reader.nextInt()
                    "legs" -> dog.legs = reader.nextInt()
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
            return dog
        }
        fun readCat(reader: JsonReader): Cat {
            var cat = Cat()
            reader.beginObject()
            while (reader.hasNext()) {
                when (reader.nextName()) {
                    "friendly" -> cat.friendly = reader.nextBoolean()
                    "sound" -> cat.sound = reader.nextString()
                    "speedInMPH" -> cat.speedInMPH = reader.nextInt()
                    "canClimb" -> cat.canClimb = reader.nextBoolean()
                    else -> reader.skipValue()
                }
            }
            reader.endObject()
            return cat
        }

    }

}