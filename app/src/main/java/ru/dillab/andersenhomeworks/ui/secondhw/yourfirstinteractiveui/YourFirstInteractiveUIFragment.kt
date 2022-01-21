package ru.dillab.andersenhomeworks.ui.secondhw.yourfirstinteractiveui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dillab.andersenhomeworks.databinding.FragmentYourFirstInteractiveUiBinding

class YourFirstInteractiveUiFragment : Fragment() {
    private var _binding: FragmentYourFirstInteractiveUiBinding? = null
    private val binding get() = _binding!!
    private var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYourFirstInteractiveUiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toastButton.setOnClickListener { navigateToHelloToastActivity() }
        binding.zeroButton.setOnClickListener {
            setCountToZero()
            setBackgroundColorGreyTo(it)
        }
        binding.countButton.setOnClickListener {
            countUp()
            evenOddChangeBackgroundColorOf(it)
            setBackgroundColorRedToZeroButton()
        }
    }

    private fun navigateToHelloToastActivity() {
        Intent(context, HelloToastActivity::class.java).also {
            it.putExtra("Count", count)
            startActivity(it)
        }
    }

    private fun setCountToZero() {
        count = 0
        binding.count.text = count.toString()
    }

    private fun setBackgroundColorGreyTo(view: View) {
        view.setBackgroundColor(Color.GRAY)
    }

    private fun countUp() {
        count++
        binding.count.text = count.toString()
    }

    private fun evenOddChangeBackgroundColorOf(view: View) {
        if (count % 2 == 0) {
            view.setBackgroundColor(Color.GREEN)
        } else {
            view.setBackgroundColor(Color.BLUE)
        }
    }

    private fun setBackgroundColorRedToZeroButton() {
        binding.zeroButton.setBackgroundColor(Color.RED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}