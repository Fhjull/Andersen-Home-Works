package ru.dillab.andersenhomeworks.ui.thirdhw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentThirdHomeWorkBinding

class ThirdHomeWorkFragment : Fragment() {
    private var _binding: FragmentThirdHomeWorkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdHomeWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstTaskButton.setOnClickListener { navigateToYourFirstInteractiveUiFragment() }
        binding.secondTaskButton.setOnClickListener { navigateToScrollingTextAppFragment() }
    }

    private fun navigateToYourFirstInteractiveUiFragment() {
        findNavController().navigate(R.id.action_thirdLessonFragment_to_secondLessonFirstTaskFragment)
    }

    private fun navigateToScrollingTextAppFragment() {
        findNavController().navigate(R.id.action_thirdLessonFragment_to_secondLessonSecondTaskFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}