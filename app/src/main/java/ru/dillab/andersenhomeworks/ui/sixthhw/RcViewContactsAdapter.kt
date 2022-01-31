package ru.dillab.andersenhomeworks.ui.sixthhw

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.RcViewContactItemBinding

class RcViewContactsAdapter(
    private val contactList: LiveData<List<RcViewContact>>,
    private val clickListener: (RcViewContact) -> Unit
) : RecyclerView.Adapter<RcViewContactsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RcViewContactItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RcViewContactItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = contactList.value?.get(position)!!
        with(holder.binding) {
            rcViewContactsImage.load(item.imageUrl) {
                crossfade(true)
                listener(
                    onSuccess = { _, _ ->
                        rcViewContactsImageProgressBar.visibility = View.GONE
                    },
                    onError = { _, _ ->
                        rcViewContactsImageProgressBar.visibility = View.GONE
                    }
                )
                transformations(CircleCropTransformation())
                error(R.drawable.error_image)
            }
            rcViewContactsFirstName.text = item.firstName
            rcViewContactsSecondName.text = item.secondName
            root.setOnClickListener { clickListener(item) }
        }
    }

    override fun getItemCount() = contactList.value?.size ?: 0
}