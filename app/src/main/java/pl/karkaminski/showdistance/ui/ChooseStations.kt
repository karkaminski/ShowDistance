package pl.karkaminski.showdistance.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.karkaminski.showdistance.R

class ChooseStations : Fragment() {

    companion object {
        fun newInstance() = ChooseStations()
    }

    private lateinit var viewModel: ChooseStationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_stations_fragment, container, false)
    }
}