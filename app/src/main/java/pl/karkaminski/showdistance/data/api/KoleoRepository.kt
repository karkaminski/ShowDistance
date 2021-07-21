package pl.karkaminski.showdistance.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import okhttp3.Cache
import okhttp3.OkHttpClient
import pl.karkaminski.showdistance.data.model.Station
import pl.karkaminski.showdistance.data.model.StationKeyword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KoleoRepository(context: Context) {

    companion object {
        private const val TAG = "Repository"
        const val BASE_URL = "https://koleo.pl/api/v2/main/"
        const val CACHE_SIZE = (5 * 1024 * 1024).toLong()
    }

    private val myCache = Cache(context.cacheDir, CACHE_SIZE)

    private fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true

        return isConnected
    }

    private val okHttpClient = OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->

            var request = chain.request()

            request = if (hasNetwork(context)!!)
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header("Cache-Control", "public, max-stale=" + 60 * 60 * 24).build()

            chain.proceed(request)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val koleoAPI = retrofit.create(KoleoAPI::class.java)

    fun getStations(): MutableLiveData<List<Station>> {
        var stationListLiveData = MutableLiveData<List<Station>>()
        koleoAPI.getStations()
            .enqueue(object : Callback<List<Station>> {
                override fun onResponse(
                    call: Call<List<Station>>,
                    response: Response<List<Station>>
                ) {
                    if (!response.isSuccessful) {
                        Log.e(TAG, "onResponse: " + response.message())
                        return
                    }
                    stationListLiveData.postValue(response.body())

                }

                override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                }
            })
        return stationListLiveData
    }

    fun getStationKeywords(): MutableLiveData<List<StationKeyword>> {
        var keywordsListLiveData = MutableLiveData<List<StationKeyword>>()
        koleoAPI.getStationKeywords()
            .enqueue(object : Callback<List<StationKeyword>> {
                override fun onResponse(
                    call: Call<List<StationKeyword>>,
                    response: Response<List<StationKeyword>>
                ) {
                    if (!response.isSuccessful) {
                        Log.e(TAG, "onResponse: " + response.message())
                        return
                    }
                    keywordsListLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<List<StationKeyword>>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                }
            })
        return keywordsListLiveData
    }
}