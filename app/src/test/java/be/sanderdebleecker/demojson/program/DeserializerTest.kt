package be.sanderdebleecker.demojson.program

import be.sanderdebleecker.demojson.program.models.Animal
import be.sanderdebleecker.demojson.program.models.Cat
import be.sanderdebleecker.demojson.program.models.Dog
import be.sanderdebleecker.demojson.program.type_adapters.AnimalAdapterFactory
import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.io.FileInputStream
import java.io.InputStream

/**
 * @author Simulan
 * @version 1.0.0
 * @since 24/06/2017
 */
@RunWith(MockitoJUnitRunner::class)
class DeserializerTest {
    val resourcesDirectory: String = "${System.getProperty("user.dir")}/app/src/main/res/raw/"

    //Simple Responses
    @Test
    fun testNamesObject() {
        // arrange
        val deserializer = Deserializer()
        val inputStream = mockContextOpenRawResource("names_object.json")

        // act
        val namesObject = deserializer.namesAsObject(inputStream)

        // assert
        assertEquals(namesObject==NamesObject(),false)
    }
    @Test
    fun testNamesArray() {
        // arrange
        val deserializer = Deserializer()
        val inputStream = mockContextOpenRawResource("names_array.json")

        // act
        val namesArray = deserializer.namesAsArray(inputStream)

        // assert
        assertEquals(namesArray.isNotEmpty(),true)
    }
    @Test
    fun testNamesObjectAsArray() {
        // arrange
        val deserializer = Deserializer()
        val inputStream = mockContextOpenRawResource("names_object.json")

        // act
        val namesArray = deserializer.namesAsEncapsulatedArray(inputStream)

        // assert
        assertEquals(namesArray.isNotEmpty(),true)
    }

    //Api Responses
    @Test
    fun testNamesInHaystack() {
        // arrange
        val deserializer = Deserializer()
        val inputStream = mockContextOpenRawResource("names_in_haystack.json")

        // act
        val namesArray = deserializer.namesInHaystack(inputStream)

        // assert
        assertEquals(namesArray.isNotEmpty(),true)
    }
    @Test
    fun testTwoTypeAdapters() {
        // arrange
        val deserializer = Deserializer()
        val inputStream = mockContextOpenRawResource("names_in_haystack.json")

        // act
        val namesArray = deserializer.namesByTwoTypeAdapters(inputStream)

        // assert
        assertEquals(namesArray.isNotEmpty(),true)
        //notes : As expected it finishes using the correct typeAdapter
    }

    //TypeAdapterFactory
    @Test
    fun serializeNonDataClasses() {
        val gson = GsonBuilder().registerTypeAdapterFactory(AnimalAdapterFactory()).create()
        val cat = Cat()
        val dog : Dog = Dog()
        val animals = arrayOf<Animal>(cat,dog)
        val json: String = gson.toJson(animals)
        println(json)
    }
    @Test
    fun deserializeNonDataClasses() {
        val gson = GsonBuilder().registerTypeAdapterFactory(AnimalAdapterFactory()).create()
        val cat = Cat()
        val dog : Dog = Dog()
        dog.legs=6
        var animals = arrayOf<Animal>(cat,dog)
        val json: String = gson.toJson(animals)
        animals = arrayOf()
        animals = gson.fromJson(json,Array<Animal>::class.java)
        assertEquals(true,animals.isNotEmpty())
    }

    //When -> thenReturn (predetermine a result)
    @Test
    fun testFun() {
        val mockDs = mock(Deserializer::class.java)
        `when`(mockDs.testFun()).thenReturn(4)
        val i = mockDs.testFun()
        assertEquals(true,i==4)
    }
    //Verify (whether method was called)
    @Test
    fun testFun2() {
        val mockDs = mock(Deserializer::class.java)
        val i = mockDs.testFun()
        verify(mockDs).testFun()
    }
    //thenThrow (e.g. for a void returning method)
    @Test
    fun testFun3() {
        val mockDs = mock(Deserializer::class.java)
        val i = mockDs.testFun()
        `when`(mockDs.testFun()).thenThrow(NullPointerException())
        try {
            verify(mockDs).testFun()
            assert(false)
        }catch (e : NullPointerException){
            assert(true)
        }
    }
    //Spy
    @Test
    fun testFun4() {
        val ds = Deserializer()
        val spyDs : Deserializer = spy(ds)
        doReturn(71).`when`(spyDs).testFun()
        val i = spyDs.testFun()
        assert(i==71)
    }

    /*
    * Mocked function Context.resources.openRawResource(resourceId : Int)
    * */
    fun mockContextOpenRawResource(fileName : String) : InputStream {
        return FileInputStream(resourcesDirectory +fileName)
    }
}