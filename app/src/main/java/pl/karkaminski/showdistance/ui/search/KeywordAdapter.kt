package pl.karkaminski.showdistance.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import pl.karkaminski.showdistance.data.model.Station
import pl.karkaminski.showdistance.data.model.StationKeyword
import pl.karkaminski.showdistance.databinding.ListItemKeywordBinding

class KeywordAdapter :
    RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder>(), Filterable {

    var keywordList = arrayListOf<StationKeyword>()
    private var keywordListFull = arrayListOf<StationKeyword>()

    var stationsList = arrayListOf<Station>()
    private var stationsListFull = arrayListOf<Station>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(
            ListItemKeywordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.binding.apply {
            textView.text = stationsList[position].name
        }
    }

    override fun getItemCount() = stationsList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredStationList = arrayListOf<Station>()
                var setOfIds = hashSetOf<Int>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredStationList.addAll(stationsListFull)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()
                    for (keyword in keywordListFull) {
                        if (keyword.keyword.lowercase().contains(filterPattern)) {
                            setOfIds.add(keyword.stationId)
                        }
                    }
                    for (station in stationsListFull){
                        if (setOfIds.contains(station.id)){
                            filteredStationList.add(station)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredStationList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                stationsList.clear()
                stationsList.addAll(results?.values as ArrayList<Station>)
                stationsList.sortWith(Comparator { lhs, rhs ->
                    if (lhs.hits!! > rhs.hits!!) -1 else if (lhs.hits < rhs.hits) 1 else 0
                })
                notifyDataSetChanged()
            }
        }
    }

    fun liveDataSetChanged() {
        notifyDataSetChanged()
        keywordListFull.addAll(keywordList)
        stationsListFull.addAll(stationsList)
    }

    inner class KeywordViewHolder(val binding: ListItemKeywordBinding) :
        RecyclerView.ViewHolder(binding.root)
}