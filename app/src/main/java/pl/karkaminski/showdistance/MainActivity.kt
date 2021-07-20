package pl.karkaminski.showdistance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
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
        var list = MutableLiveData<List<Station>>()

        list.observe(this, {
            stations ->
            if (stations != null) {
                for (station in stations) {
                    var text = ""
                    text += "ID: ${station.id} \n"
                    text += "name: ${station.name} \n"
                    text += "lat: ${station.latitude} \n"
                    text += "lon: ${station.longitude} \n"

                    textView.append(text)
                }
            }

        })

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://koleo.pl/api/v2/main/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(KoleoAPI::class.java)
        val call = api.getStations()

        call.enqueue(object : Callback<List<Station>> {
            override fun onResponse(call: Call<List<Station>>, response: Response<List<Station>>) {
//                Log.i(TAG, "onResponse: ")
                if (!response.isSuccessful) {
                    textView.text =  "Error ${response.code()}"
                    return
                }
                list.postValue(response.body())

            }
            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.message)
            }
        })
    }

}