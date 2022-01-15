package ru.dillab.andersenhomeworks.ui.secondlesson.scrollingtextapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.dillab.andersenhomeworks.databinding.FragmentScrollingTextAppBinding

class ScrollingTextAppFragment : Fragment() {
    private var binding: FragmentScrollingTextAppBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding =
            FragmentScrollingTextAppBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}