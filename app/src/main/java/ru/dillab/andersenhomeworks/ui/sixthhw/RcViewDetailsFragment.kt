package ru.dillab.andersenhomeworks.ui.sixthhw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentRcViewDetailsBinding


class RcViewDetailsFragment : Fragment() {
    private var _binding: FragmentRcViewDetailsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: RcViewContactsViewModel by activityViewModels()
    private val args: RcViewDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRcViewDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contactId = args.contactId
        val contact = sharedViewModel.findContactById(contactId)

        if (contact != null) {
            binding.rcViewDetailsFirstNameTextField.text = contact.firstName
            binding.rcViewDetailsSecondNameTextField.text = contact.secondName
            binding.rcViewDetailsNumberTextField.text = contact.number
            binding.rcViewDetailsImageView.load(contact.imageUrl) {
                crossfade(true)
                listener(
                    onSuccess = { _, _ ->
                        binding.rcViewDetailsImageProgressBar.visibility = View.GONE
                    },
                    onError = { _, _ ->
                        binding.rcViewDetailsImageProgressBar.visibility = View.GONE
                    }
                )
                error(R.drawable.error_image)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}