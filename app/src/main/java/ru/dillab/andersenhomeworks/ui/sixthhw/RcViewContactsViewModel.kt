package ru.dillab.andersenhomeworks.ui.sixthhw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RcViewContactsViewModel : ViewModel() {

    companion object {
        const val NUMBER_OF_CONTACTS = 110
    }

    private var _rcViewContacts = MutableLiveData<List<RcViewContact>>()
    val rcViewContacts: LiveData<List<RcViewContact>> = _rcViewContacts

    init {
        _rcViewContacts.value = contactList()
    }

    fun findContactById(id: Int): RcViewContact? {
        var contact: RcViewContact? = null
        rcViewContacts.value?.forEach {
            if (it.id == id) {
                contact = it
            }
        }
        return contact
    }

    private fun contactList(): List<RcViewContact> {
        val contactList = mutableListOf<RcViewContact>()
        for (i in 0..NUMBER_OF_CONTACTS) {
            val firstName = RcViewData().names.random()
            val secondName = RcViewData().surnames.random()
            val number = "+79" + (100000000..999999999).random()
            val image = "https://picsum.photos/id/$i/200/200"
            contactList.add(RcViewContact(i, "$i $firstName", secondName, number, image))
        }
        return contactList
    }
}