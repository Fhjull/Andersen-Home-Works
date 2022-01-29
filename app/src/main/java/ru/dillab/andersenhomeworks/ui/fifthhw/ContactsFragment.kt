package ru.dillab.andersenhomeworks.ui.fifthhw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.contacts.observe(viewLifecycleOwner) {
            val contact1 = it[0]
            val contact2 = it[1]
            val contact3 = it[2]
            val contact4 = it[3]
            binding.contactButton1.text =
                getString(R.string.first_and_second_names, contact1.firstName, contact1.secondName)
            binding.contactButton2.text =
                getString(R.string.first_and_second_names, contact2.firstName, contact2.secondName)
            binding.contactButton3.text =
                getString(R.string.first_and_second_names, contact3.firstName, contact3.secondName)
            binding.contactButton4.text =
                getString(R.string.first_and_second_names, contact4.firstName, contact4.secondName)
        }

        binding.contactButton1.setOnClickListener {
            sharedViewModel.setCurrentContactIndex(0)
            openContactDetailsFragment()
        }
        binding.contactButton2.setOnClickListener {
            sharedViewModel.setCurrentContactIndex(1)
            openContactDetailsFragment()
        }
        binding.contactButton3.setOnClickListener {
            sharedViewModel.setCurrentContactIndex(2)
            openContactDetailsFragment()
        }
        binding.contactButton4.setOnClickListener {
            sharedViewModel.setCurrentContactIndex(3)
            openContactDetailsFragment()
        }
    }

    private fun openContactDetailsFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            val container = if ((resources.getBoolean(R.bool.isTablet))) {
                // Don't add DetailsFragment to BackStack on tablets
                R.id.contacts_details_container
            } else {
                addToBackStack(null)
                R.id.contacts_container
            }
            replace(container, DetailsFragment())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}