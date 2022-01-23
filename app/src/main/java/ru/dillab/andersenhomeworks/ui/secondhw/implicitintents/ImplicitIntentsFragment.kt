package ru.dillab.andersenhomeworks.ui.secondhw.implicitintents

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import ru.dillab.andersenhomeworks.databinding.FragmentImplicitIntentsBinding

class ImplicitIntentsFragment : Fragment() {
    private var _binding: FragmentImplicitIntentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImplicitIntentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.openWebsiteButton.setOnClickListener { openWebSite() }
        binding.openLocationButton.setOnClickListener { openLocation() }
        binding.shareTextButton.setOnClickListener { shareText() }
        binding.takePictureButton.setOnClickListener { takePicture() }
    }

    private fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            if (it.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(it)
            } else {
                Toast.makeText(context, "Cannot take picture", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun shareText() {
        val txt = binding.shareEdittext.text.toString()
        ShareCompat.IntentBuilder(requireContext())
            .setType("text/plain")
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()
    }

    private fun openLocation() {
        val location = binding.locationEdittext.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$location")
        Intent(Intent.ACTION_VIEW, addressUri).also {
            // We can use either try/catch or if(resolveActivity), see below
            try {
                startActivity(it)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "Location activity not found", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun openWebSite() {
        val url = binding.websiteEdittext.text.toString()
        val webPage = Uri.parse(url)
        Intent(Intent.ACTION_VIEW, webPage).also {
            // If we use resolveActivity, add queries in AndroidManifest
            if (it.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(it)
            } else {
                Toast.makeText(context, "Web activity not found", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}