package ru.dillab.andersenhomeworks.ui.sixthhw

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.dillab.andersenhomeworks.databinding.FragmentRcViewContactsBinding


class RcViewContactsFragment : Fragment() {
    private var _binding: FragmentRcViewContactsBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: RcViewContactsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRcViewContactsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()
    }

    private fun setupRecycleView() {
        val recycleView = binding.rcViewContactsRecycleView
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)
        val data = sharedViewModel.rcViewContacts
        recycleView.adapter = RcViewContactsAdapter(data) {
            openDetailsFragment(it.id)
        }
    }

    private fun openDetailsFragment(id: Int) {
        val action =
            RcViewContactsFragmentDirections.actionRcViewContactsFragmentToRcViewDetailsFragment(id)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}