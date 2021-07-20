package pl.karkaminski.showdistance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import pl.karkaminski.showdistance.data.api.KoleoRepository
import pl.karkaminski.showdistance.data.model.Station
import pl.karkaminski.showdistance.data.model.StationKeyword

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = KoleoRepository(application.applicationContext)

    fun getStations(): MutableLiveData<List<Station>> {
        return repository.getStations()
    }

    fun getStationKeywords(): MutableLiveData<List<StationKeyword>> {
        return repository.getStationKeywords()
    }

}