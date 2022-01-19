package ru.dillab.andersenhomeworks.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.secondLessonButton.setOnClickListener { navigateToSecondLessonFragment() }
        binding.thirdLessonButton.setOnClickListener { navigateToThirdLessonFragment() }
        binding.forthLessonButton.setOnClickListener { navigateToClocksFragment() }
    }

    private fun navigateToSecondLessonFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_secondLessonFragment)
    }

    private fun navigateToThirdLessonFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_thirdLessonFragment)
    }

    private fun navigateToClocksFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_clocksFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}