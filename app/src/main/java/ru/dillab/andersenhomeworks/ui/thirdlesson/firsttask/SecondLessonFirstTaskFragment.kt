package ru.dillab.andersenhomeworks.ui.thirdlesson.firsttask

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.dillab.andersenhomeworks.R
import ru.dillab.andersenhomeworks.databinding.FragmentThirdLessonFirstTaskBinding

class SecondLessonFirstTaskFragment : Fragment() {
    private var binding: FragmentThirdLessonFirstTaskBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding =
            FragmentThirdLessonFirstTaskBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getStringOnEnterPressedAndLoadImage()
    }

    private fun getStringOnEnterPressedAndLoadImage() {
        binding?.thirdLessonFirstTaskEditText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_ACTION_DONE ||
                event != null &&
                event.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                if (event == null || !event.isShiftPressed) {
                    val url = v.text.toString()
                    loadImage(url)
                }
            }
            false
        }
    }

    private fun loadImage(url: String) {
        val image = binding?.thirdLessonFirstTaskImage!!
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.error_image)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        val errorMessage = e?.message?.split("\n")
                            ?.get(0)    // Only first string of error message
                        showErrorToast(errorMessage)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Do nothing extra
                        return false
                    }
                }
            )
            .into(image)
    }

    fun showErrorToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}