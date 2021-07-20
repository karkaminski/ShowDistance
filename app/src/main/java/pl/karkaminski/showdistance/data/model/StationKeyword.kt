package pl.karkaminski.showdistance.data.model

import com.google.gson.annotations.SerializedName

data class StationKeyword(
    val id: Int,
    val keyword: String,
    @SerializedName("station_id")
    val stationId: Int
)