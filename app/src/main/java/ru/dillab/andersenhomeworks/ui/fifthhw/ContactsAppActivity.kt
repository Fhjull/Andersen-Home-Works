package ru.dillab.andersenhomeworks.ui.fifthhw

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import ru.dillab.andersenhomeworks.R

class ContactsAppActivity : AppCompatActivity(R.layout.activity_contacts_app) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.contacts_container, ContactsFragment())
        }
    }
}