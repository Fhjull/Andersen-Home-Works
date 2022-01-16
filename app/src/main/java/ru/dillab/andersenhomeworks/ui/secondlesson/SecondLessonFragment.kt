package ru.dillab.andersenhomeworks.ui.secondlesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentSecondLessonBinding

class SecondLessonFragment : Fragment() {
    private var _binding: FragmentSecondLessonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fundamentals012Button.setOnClickListener { navigateToYourFirstInteractiveUiFragment() }
        binding.scrollingTextAppButton.setOnClickListener { navigateToScrollingTextAppFragment() }
    }

    private fun navigateToYourFirstInteractiveUiFragment() {
        findNavController().navigate(R.id.action_secondLessonFragment_to_yourFirstInteractiveUiFragment)
    }

    private fun navigateToScrollingTextAppFragment() {
        findNavController().navigate(R.id.action_secondLessonFragment_to_scrollingTextAppFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}