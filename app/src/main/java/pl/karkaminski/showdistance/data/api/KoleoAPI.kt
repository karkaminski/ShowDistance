package pl.karkaminski.showdistance.data.api

import pl.karkaminski.showdistance.data.model.Station
import pl.karkaminski.showdistance.data.model.StationKeyword
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface KoleoAPI {

    @Headers("X-KOLEO-Version: 1")
    @GET("stations")
    fun getStations () : Call<List<Station>>

    @Headers("X-KOLEO-Version: 1")
    @GET("station_keywords")
    fun getStationKeywords () : Call<List<StationKeyword>>
 }