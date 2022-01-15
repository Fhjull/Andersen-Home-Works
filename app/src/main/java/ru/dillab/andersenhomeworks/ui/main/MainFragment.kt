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
    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentMainBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.secondLessonButton?.setOnClickListener { navigateToSecondLessonFragment() }
        binding?.thirdLessonButton?.setOnClickListener { navigateToThirdLessonFragment() }
    }

    private fun navigateToSecondLessonFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_secondLessonFragment)
    }

    private fun navigateToThirdLessonFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_thirdLessonFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}