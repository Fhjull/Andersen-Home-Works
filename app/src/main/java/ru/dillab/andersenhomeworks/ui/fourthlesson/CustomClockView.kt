package ru.dillab.andersenhomeworks.ui.fourthlesson

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import ru.dillab.andersenhomeworks.R
import java.util.*
import kotlin.properties.Delegates

class CustomClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var radius by Delegates.notNull<Float>()
    private var centerX by Delegates.notNull<Float>()
    private var centerY by Delegates.notNull<Float>()
    private var hourIndexesLength by Delegates.notNull<Float>()
    private var handHourLength by Delegates.notNull<Float>()
    private var handMinuteLength by Delegates.notNull<Float>()
    private var handSecondLength by Delegates.notNull<Float>()
    private var handHourColor = DEFAULT_HAND_HOUR_COLOR
    private var handMinuteColor = DEFAULT_HAND_MINUTE_COLOR
    private var handSecondColor = DEFAULT_HAND_SECOND_COLOR
    private val paint = Paint()

    companion object {
        private const val DEFAULT_SIZE = 300
        private const val STROKE_WIDTH = 10f
        private const val DEFAULT_HAND_LENGTH = -1f     // Don't change
        private const val CLOCK_FACE_COLOR = Color.BLACK
        private const val DEFAULT_HAND_HOUR_COLOR = Color.DKGRAY
        private const val DEFAULT_HAND_MINUTE_COLOR = Color.BLUE
        private const val DEFAULT_HAND_SECOND_COLOR = Color.RED
    }

    init {
        getAttributesFromXML(attrs)
        initializePaint()
    }

    private fun getAttributesFromXML(attrs: AttributeSet?) {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomClockView)
            handHourColor = ta.getColor(
                R.styleable.CustomClockView_hand_hour_color, DEFAULT_HAND_HOUR_COLOR
            )
            handMinuteColor = ta.getColor(
                R.styleable.CustomClockView_hand_minute_color, DEFAULT_HAND_MINUTE_COLOR
            )
            handSecondColor = ta.getColor(
                R.styleable.CustomClockView_hand_second_color, DEFAULT_HAND_SECOND_COLOR
            )
            handHourLength = ta.getDimension(
                R.styleable.CustomClockView_hand_hour_length, DEFAULT_HAND_LENGTH
            )
            handMinuteLength =
                ta.getDimension(
                    R.styleable.CustomClockView_hand_minute_length, DEFAULT_HAND_LENGTH
                )
            handSecondLength =
                ta.getDimension(
                    R.styleable.CustomClockView_hand_second_length, DEFAULT_HAND_LENGTH
                )
            ta.recycle()
        }
    }

    private fun initializePaint() {
        paint.apply {
            isAntiAlias = true
            color = CLOCK_FACE_COLOR
            style = Paint.Style.STROKE
            strokeWidth = STROKE_WIDTH
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(size, size)
    }

    private fun resolveDefaultSize(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> context.dpToPx(DEFAULT_SIZE).toInt()
            else -> MeasureSpec.getSize(spec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val canvasSize = width
        radius = (canvasSize - STROKE_WIDTH) / 2f
        centerX = canvasSize / 2f
        centerY = canvasSize / 2f
        hourIndexesLength = radius / 10f
        handHourLength = if (handHourLength == DEFAULT_HAND_LENGTH) {
            radius * 0.55f
        } else {
            handHourLength
        }
        handMinuteLength = if (handMinuteLength == DEFAULT_HAND_LENGTH) {
            radius * 0.7f
        } else {
            handMinuteLength
        }
        handSecondLength = if (handSecondLength == DEFAULT_HAND_LENGTH) {
            radius * 0.8f
        } else {
            handSecondLength
        }

        // Move (0, 0) coordinate of canvas to center
        canvas.translate(centerX, centerY)

        drawClockFace(canvas)
        drawHourHand(canvas)
        drawMinuteHand(canvas)
        drawSecondHand(canvas)

        postInvalidateDelayed(1000)
    }

    private fun drawClockFace(canvas: Canvas) {
        canvas.save()
        paint.color = CLOCK_FACE_COLOR
        paint.strokeWidth = STROKE_WIDTH
        canvas.drawCircle(0f, 0f, radius, paint)
        repeat(12) {
            canvas.drawLine(0f, radius - hourIndexesLength, 0f, radius, paint)
            canvas.rotate(30f)
        }
        canvas.restore()
    }

    private fun drawHourHand(canvas: Canvas) {
        val hours = Calendar.getInstance().get(Calendar.HOUR)
        val minutes = Calendar.getInstance().get(Calendar.MINUTE)
        val angle = hours % 12 * 30f + minutes / 2f - 180
        canvas.save()
        canvas.rotate(angle)
        paint.color = handHourColor
        paint.strokeWidth = 8f
        canvas.drawLine(0f, -radius / 10, 0f, handHourLength, paint)
        canvas.restore()
    }

    private fun drawMinuteHand(canvas: Canvas) {
        val minutes = Calendar.getInstance().get(Calendar.MINUTE)
        val seconds = Calendar.getInstance().get(Calendar.SECOND)
        val angle = minutes * 6f + seconds / 10f - 180
        canvas.save()
        canvas.rotate(angle)
        paint.color = handMinuteColor
        paint.strokeWidth = 6f
        canvas.drawLine(0f, -radius / 10, 0f, handMinuteLength, paint)
        canvas.restore()
    }

    private fun drawSecondHand(canvas: Canvas) {
        val seconds = Calendar.getInstance().get(Calendar.SECOND)
        val angle = seconds * 6f - 180
        canvas.save()
        canvas.rotate(angle)
        paint.color = handSecondColor
        paint.strokeWidth = 4f
        canvas.drawLine(0f, -radius / 10, 0f, handSecondLength, paint)
        canvas.restore()
    }
}

fun Context.dpToPx(dp: Int): Float {
    return dp.toFloat() * this.resources.displayMetrics.density
}