package ru.dillab.andersenhomeworks.ui.secondhw

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentSecondHomeWorkBinding
import ru.dillab.andersenhomeworks.ui.secondhw.shoppinglistapp.ShoppingListActivity

class SecondHomeWorkFragment : Fragment() {
    private var _binding: FragmentSecondHomeWorkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondHomeWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fundamentals012Button.setOnClickListener { navigateToYourFirstInteractiveUiFragment() }
        binding.scrollingTextAppButton.setOnClickListener { navigateToScrollingTextAppFragment() }
        binding.shoppingListAppButton.setOnClickListener { navigateToShoppingListAppActivity() }
        binding.onSaveInstanceStateButton.setOnClickListener { navigateToOnSaveInstanceSaveFragment() }
    }

    private fun navigateToYourFirstInteractiveUiFragment() {
        findNavController().navigate(R.id.action_secondLessonFragment_to_yourFirstInteractiveUiFragment)
    }

    private fun navigateToScrollingTextAppFragment() {
        findNavController().navigate(R.id.action_secondLessonFragment_to_scrollingTextAppFragment)
    }

    private fun navigateToShoppingListAppActivity() {
        Intent(context, ShoppingListActivity::class.java).also {
            startActivity(it)
        }
    }

    private fun navigateToOnSaveInstanceSaveFragment() {
        findNavController().navigate(R.id.action_secondLessonFragment_to_onSaveInstateStateFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}