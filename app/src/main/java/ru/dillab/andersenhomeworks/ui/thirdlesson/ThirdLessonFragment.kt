package ru.dillab.andersenhomeworks.ui.thirdlesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentThirdLessonBinding


class ThirdLessonFragment : Fragment() {
    private var binding: FragmentThirdLessonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentThirdLessonBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.firstTaskButton?.setOnClickListener { navigateToYourFirstInteractiveUiFragment() }
        binding?.secondTaskButton?.setOnClickListener { navigateToScrollingTextAppFragment() }
    }

    private fun navigateToYourFirstInteractiveUiFragment() {
        findNavController().navigate(R.id.action_thirdLessonFragment_to_secondLessonFirstTaskFragment)
    }

    private fun navigateToScrollingTextAppFragment() {
        findNavController().navigate(R.id.action_thirdLessonFragment_to_secondLessonSecondTaskFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}