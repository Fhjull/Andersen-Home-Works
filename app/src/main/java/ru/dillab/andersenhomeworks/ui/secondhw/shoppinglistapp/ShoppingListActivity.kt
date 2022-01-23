package ru.dillab.andersenhomeworks.ui.secondhw.shoppinglistapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.ActivityShoppingListBinding

class ShoppingListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingListBinding
    var shoppingList = ""

    companion object {
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("testing", "ShoppingListActivity onCreate()")
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = getString(R.string.shopping_list_activity_title)

        binding.addItemButton.setOnClickListener { navigateToAddItemActivity() }

        if (savedInstanceState != null) {
            Log.i(
                "testing", "ShoppingListActivity savedInstanceState != null\n" +
                        "shoppingList = $shoppingList"
            )
            shoppingList = savedInstanceState.getString("shop_list").toString()
            Log.i(
                "testing", "shoppingList after getString() = $shoppingList"
            )
            binding.shopTextView.text = shoppingList
        }

        binding.locateStoreButton.setOnClickListener { locateStore() }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun locateStore() {
        val particularStore = binding.locateStoreEdittext.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$particularStore")
        Intent(Intent.ACTION_VIEW, addressUri).also {
            if (it.resolveActivity(packageManager) != null) {
            // try {
                startActivity(it)
            // } catch (e: ActivityNotFoundException) {
            } else {
                Toast.makeText(this, "Cannot open map", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("testing", "ShoppingListActivity onSaveInstanceState()\nshoppingList = $shoppingList")
        outState.putString("shop_list", shoppingList)
    }

    private fun navigateToAddItemActivity() {
        Log.i("testing", "ShoppingListActivity navigateToAddItemActivity()")
        Intent(this, AddItemActivity::class.java).also {
            startActivityForResult(it, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("testing", "ShoppingListActivity onActivityResult()")
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                val newFood = data?.getStringExtra(AddItemActivity.EXTRA_REPLY)
                if (newFood != null && !shoppingList.contains(newFood)) {
                    shoppingList += newFood + "\n"
                    binding.shopTextView.text = shoppingList
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("testing", "ShoppingListActivity onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("testing", "ShoppingListActivity onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i("testing", "ShoppingListActivity onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("testing", "ShoppingListActivity onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("testing", "ShoppingListActivity onDestroy()")
    }
}