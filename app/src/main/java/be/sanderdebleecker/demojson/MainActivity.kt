package be.sanderdebleecker.demojson

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import be.sanderdebleecker.demojson.program.Deserializer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var deserializer = Deserializer()
        var names = deserializer.namesAsObject(resources.openRawResource(R.raw.names_object))
        Log.d(Log.DEBUG.toString(),names.toString())
    }
}
