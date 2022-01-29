package ru.dillab.andersenhomeworks.ui.fifthhw

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactsViewModel : ViewModel() {

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private var _currentContactIndex = MutableLiveData<Int>()
    val currentContactIndex: LiveData<Int> = _currentContactIndex

    init {
        _contacts.value = listOf(
            Contact(0, "John", "Lennon", "+79211234567"),
            Contact(1, "Paul", "McCartney", "+79119876543"),
            Contact(2, "George", "Harrison", "+79055552205"),
            Contact(3, "Ringo", "Starr", "+79604562195")
        )
    }

    fun setCurrentContactIndex(index: Int) {
        _currentContactIndex.value = index
    }

    fun changeContact(
        contact: Contact,
        newFirstName: String,
        newSecondName: String,
        newNumber: String
    ) {
        val indexOfContact = contacts.value?.indexOf(contact)
        if (indexOfContact != null && indexOfContact != -1) {
            val id = _contacts.value?.get(indexOfContact)?.id!!
            val list = _contacts.value?.toMutableList()
            list?.set(indexOfContact, Contact(id, newFirstName, newSecondName, newNumber))
            _contacts.value = list!!
        }
    }
}