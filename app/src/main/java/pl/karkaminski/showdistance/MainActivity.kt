package pl.karkaminski.showdistance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import pl.karkaminski.showdistance.data.api.KoleoAPI
import pl.karkaminski.showdistance.data.model.Station
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    
    companion object {
        private const val TAG = "KOLEOAPP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.text_view)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        viewModel.getStations().observe(this, {
//            stations ->
//            if (stations != null) {
//                for (station in stations) {
//                    var text = ""
//                    text += "ID: ${station.id} \n"
//                    text += "name: ${station.name} \n"
//                    text += "lat: ${station.latitude} \n"
//                    text += "lon: ${station.longitude} \n"
//
//                    textView.append(text)
//                }
//            }
//        })

        viewModel.getStationKeywords().observe(this, {
            keywords ->
            if (keywords != null) {
                for (word in keywords) {
                    var text = ""
                    text += "id: ${word.id} \n"
                    text += "keyword: ${word.keyword} \n"
                    text += "stationId: ${word.stationId} \n"

                    Log.i(TAG, "id: ${word.id}, keyword: ${word.keyword}")

                    textView.append(text)
                }
            }
        })




    }

}