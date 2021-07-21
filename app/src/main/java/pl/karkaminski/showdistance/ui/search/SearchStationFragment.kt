package pl.karkaminski.showdistance.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pl.karkaminski.showdistance.data.model.Station
import pl.karkaminski.showdistance.data.model.StationKeyword
import pl.karkaminski.showdistance.databinding.SearchStationFragmentBinding
import java.util.*
import kotlin.collections.ArrayList

class SearchStationFragment : Fragment() {

    companion object {
        fun newInstance() = SearchStationFragment()
    }

    private lateinit var viewModel: SearchStationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SearchStationFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SearchStationViewModel::class.java)

        val keywordAdapter = KeywordAdapter()
        binding.recyclerView.adapter = keywordAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                keywordAdapter.filter.filter(newText)
                return false
            }
        })

        viewModel.getStationKeywords().observe(
            viewLifecycleOwner
        ) { keywordList ->
            keywordAdapter.apply {
                if (keywordList != null) {
                    this.keywordList = keywordList as ArrayList<StationKeyword>
                    liveDataSetChanged()
                }
            }
        }

        viewModel.getStations().observe(
            viewLifecycleOwner
        ) { stationsList ->
            keywordAdapter.apply {
                if (stationsList != null) {
                    this.stationsList = stationsList as ArrayList<Station>
                    liveDataSetChanged()
                }
            }
        }

        return binding.root
    }


}