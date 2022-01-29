package ru.dillab.andersenhomeworks.ui.fifthhw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: ContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.currentContactIndex.observe(viewLifecycleOwner) { setTextFields() }

        binding.contactDetailsEditButton.setOnClickListener { setEditFieldsToVisible() }
        binding.contactDetailsSaveButton.setOnClickListener { saveChangesOfContact() }
    }

    private fun setTextFields() {
        val contact = getContactFromModel()
        if (contact != null) {
            binding.contactDetailsFirstNameTextField.text = contact.firstName
            binding.contactDetailsSecondNameTextField.text = contact.secondName
            binding.contactDetailsNumberTextField.text = contact.number
        }
    }

    private fun getContactFromModel(): Contact? {
        val index = sharedViewModel.currentContactIndex.value
        if (index != null) {
            return sharedViewModel.contacts.value?.get(index)
        }
        return null
    }

    private fun setEditFieldsToVisible() {
        binding.contactDetailsFirstNameEditText.visibility = View.VISIBLE
        binding.contactDetailsSecondNameEditText.visibility = View.VISIBLE
        binding.contactDetailsNumberEditText.visibility = View.VISIBLE
        binding.contactDetailsSaveButton.visibility = View.VISIBLE
        setEditFields()
    }

    private fun setEditFields() {
        val contact = getContactFromModel()
        if (contact != null) {
            binding.contactDetailsFirstNameEditText.setText(contact.firstName)
            binding.contactDetailsSecondNameEditText.setText(contact.secondName)
            binding.contactDetailsNumberEditText.setText(contact.number)
        }
    }

    private fun saveChangesOfContact() {
        val contact = getContactFromModel()
        if (contact != null) {
            val newFirstName = binding.contactDetailsFirstNameEditText.text.toString()
            val newSecondName = binding.contactDetailsSecondNameEditText.text.toString()
            val newNumber = binding.contactDetailsNumberEditText.text.toString()
            sharedViewModel.changeContact(contact, newFirstName, newSecondName, newNumber)
            closeEditFields()
        }
    }

    private fun closeEditFields() {
        if (resources.getBoolean(R.bool.isTablet)) {
            setEditFieldsToGone()
            setTextFields()
        } else {
            returnToContactList()
        }
    }

    private fun setEditFieldsToGone() {
        binding.contactDetailsFirstNameEditText.visibility = View.GONE
        binding.contactDetailsSecondNameEditText.visibility = View.GONE
        binding.contactDetailsNumberEditText.visibility = View.GONE
        binding.contactDetailsSaveButton.visibility = View.GONE
    }

    private fun returnToContactList() {
        parentFragmentManager.popBackStack()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}