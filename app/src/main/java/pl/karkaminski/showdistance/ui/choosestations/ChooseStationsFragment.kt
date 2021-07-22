package pl.karkaminski.showdistance.ui.choosestations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import pl.karkaminski.showdistance.databinding.ChooseStationsFragmentBinding

class ChooseStationsFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseStationsFragment()
    }

    private lateinit var viewModel: ChooseStationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ChooseStationsFragmentBinding.inflate(inflater, container, false)

        binding.buttonStart.setOnClickListener {
            (it as Button).text = "Kraków główny - test tekstu"
        }

        binding.buttonDestination.setOnClickListener {
            (it as Button).text = "Warszawa Centralna - test tekstu"
        }

        return binding.root
    }

}