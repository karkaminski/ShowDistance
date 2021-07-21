package pl.karkaminski.showdistance.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import pl.karkaminski.showdistance.data.model.StationKeyword
import pl.karkaminski.showdistance.databinding.ListItemKeywordBinding

class KeywordAdapter :
    RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder>(), Filterable {

    var keywordList = arrayListOf<StationKeyword>()
    private var keywordListFull = arrayListOf<StationKeyword>()

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
            textView.text = keywordList[position].keyword
        }
    }

    override fun getItemCount() = keywordList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = arrayListOf<StationKeyword>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(keywordListFull)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()
                    for (keyword in keywordListFull) {
                        if (keyword.keyword.lowercase().contains(filterPattern)) {
                            filteredList.add(keyword)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                keywordList.clear()
                keywordList.addAll(results?.values as ArrayList<StationKeyword>)
                notifyDataSetChanged()
            }
        }
    }

    fun liveDataSetChanged(){

        notifyDataSetChanged()
        keywordListFull.addAll(keywordList)
    }

    inner class KeywordViewHolder(val binding: ListItemKeywordBinding) :
        RecyclerView.ViewHolder(binding.root)
}