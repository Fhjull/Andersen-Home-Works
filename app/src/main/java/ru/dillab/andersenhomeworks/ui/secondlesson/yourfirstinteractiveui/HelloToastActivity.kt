package ru.dillab.andersenhomeworks.ui.secondlesson.yourfirstinteractiveui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.ActivityHelloToastBinding

class HelloToastActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelloToastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloToastBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this.title = getString(R.string.hello_toast_title)

        val count = intent.extras?.getInt("Count")
        binding.helloToastActivityText.text = getString(R.string.toast_message, count)
    }
}