package ru.dillab.andersenhomeworks.ui.thirdlesson.secondtask

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.withContext
import ru.dillab.andersenhomeworks.databinding.FragmentThirdLessonSecondTaskBinding

class SecondLessonSecondTaskFragment : Fragment() {
    private var binding: FragmentThirdLessonSecondTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding =
            FragmentThirdLessonSecondTaskBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // val width = resources.configuration.screenWidthDp
        //
        // binding?.text3?.text = width.toString()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

