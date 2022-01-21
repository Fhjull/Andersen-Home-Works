package ru.dillab.andersenhomeworks.ui.secondhw.shoppinglistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddItemBinding

    companion object {
        const val EXTRA_REPLY = "REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("testing", "AddItemActivity onCreate()")
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.add_item_activity_title)

        binding.CookiesButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.applesButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.breadButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.cheeseButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.juiceButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.onionButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.orangeButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.riceButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.tomatoButton.setOnClickListener { returnToShoppingListActivity(it as Button) }
        binding.waterButton.setOnClickListener { returnToShoppingListActivity(it as Button) }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun returnToShoppingListActivity(view: Button) {
        Log.i("testing", "AddItemActivity returnToShoppingListActivity()")
        Intent(this, ShoppingListActivity::class.java).also {
            it.putExtra(EXTRA_REPLY, view.text)
            setResult(RESULT_OK, it)
        }
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onStart() {
        super.onStart()
        Log.i("testing", "AddItemActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("testing", "AddItemActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i("testing", "AddItemActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("testing", "AddItemActivity onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("testing", "AddItemActivity onDestroy()")
    }
}