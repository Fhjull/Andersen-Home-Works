package ru.dillab.andersenhomeworks.ui.secondhw.onsaveinstancestate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dillab.andersenhomeworks.databinding.FragmentOnSaveInstateStateBinding

class OnSaveInstateStateFragment : Fragment() {
    private var _binding: FragmentOnSaveInstateStateBinding? = null
    private val binding get() = _binding!!
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnSaveInstateStateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count")
        }

        showCount()

        binding.onSaveStateButton.setOnClickListener {
            incrementCount()
            showCount()
        }
    }

    private fun incrementCount() {
        count++
    }

    private fun showCount() {
        binding.onSaveStateTextView.text = count.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("count", count)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}