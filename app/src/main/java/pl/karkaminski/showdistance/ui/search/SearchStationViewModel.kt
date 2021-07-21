package pl.karkaminski.showdistance.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.karkaminski.showdistance.data.api.KoleoRepository
import pl.karkaminski.showdistance.data.model.Station
import pl.karkaminski.showdistance.data.model.StationKeyword

class SearchStationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = KoleoRepository(application.applicationContext)

    fun getStations(): MutableLiveData<List<Station>> {
        return repository.getStations()
    }

    fun getStationKeywords(): MutableLiveData<List<StationKeyword>> {
        return repository.getStationKeywords()
    }
}