package ru.dillab.andersenhomeworks.ui.secondlesson.yourfirstinteractiveui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentYourFirstInteractiveUiBinding

class YourFirstInteractiveUiFragment : Fragment() {
    private var binding: FragmentYourFirstInteractiveUiBinding? = null
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding =
            FragmentYourFirstInteractiveUiBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toastButton?.setOnClickListener { showToast() }
        binding?.zeroButton?.setOnClickListener {
            setCountToZero()
            setBackgroundColorGreyTo(it)
        }
        binding?.countButton?.setOnClickListener {
            countUp()
            evenOddChangeBackgroundColorOf(it)
            setBackgroundColorRedToZeroButton()
        }
    }

    private fun showToast() {
        Toast.makeText(context, R.string.toast_message, Toast.LENGTH_SHORT).show()
    }

    private fun setCountToZero() {
        count = 0
        binding?.count?.text = count.toString()
    }

    private fun setBackgroundColorGreyTo(view: View) {
        view.setBackgroundColor(Color.GRAY)
    }

    private fun countUp() {
        count++
        binding?.count?.text = count.toString()
    }

    private fun evenOddChangeBackgroundColorOf(view: View) {
        if (count % 2 == 0) {
            view.setBackgroundColor(Color.GREEN)
        } else {
            view.setBackgroundColor(Color.BLUE)
        }
    }

    private fun setBackgroundColorRedToZeroButton() {
        binding?.zeroButton?.setBackgroundColor(Color.RED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}