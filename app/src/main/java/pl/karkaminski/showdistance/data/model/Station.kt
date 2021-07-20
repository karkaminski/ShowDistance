package pl.karkaminski.showdistance.data.model

import com.google.gson.annotations.SerializedName

data class Station(
    val id: Int,
    val name: String?,
    @SerializedName("name_slug")
    val nameSlug: String?,
    val latitude: Double?,
    val longitude: Double?,
    val hits: Int?,
    val ibnr: Int?,
    val city: String?,
    val region: String?,
    val country: String?,
    @SerializedName("localised_name")
    val localisedName: String?
)