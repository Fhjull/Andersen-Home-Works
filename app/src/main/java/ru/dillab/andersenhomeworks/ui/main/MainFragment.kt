package ru.dillab.andersenhomeworks.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentMainBinding
import ru.dillab.andersenhomeworks.ui.fifthhw.ContactsAppActivity
import ru.dillab.andersenhomeworks.ui.sixthhw.RcViewContactsAppActivity

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
        binding.secondHomeWorkButton.setOnClickListener { navigateToSecondHomeWorkFragment() }
        binding.thirdHomeWorkButton.setOnClickListener { navigateToThirdHomeWorkFragment() }
        binding.fourthHomeWorkButton.setOnClickListener { navigateToClocksFragment() }
        binding.fifthHomeWorkButton.setOnClickListener { navigateToContactsAppActivity() }
        binding.sixthHomeWorkButton.setOnClickListener { navigateToRcViewContactsAppActivity() }
    }

    private fun navigateToSecondHomeWorkFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_secondLessonFragment)
    }

    private fun navigateToThirdHomeWorkFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_thirdLessonFragment)
    }

    private fun navigateToClocksFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_clocksFragment)
    }

    private fun navigateToContactsAppActivity() {
        Intent(context, ContactsAppActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun navigateToRcViewContactsAppActivity() {
        Intent(context, RcViewContactsAppActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}