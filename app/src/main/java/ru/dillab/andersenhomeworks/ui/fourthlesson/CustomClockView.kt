package ru.dillab.andersenhomeworks.ui.forthlesson

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import ru.dillab.andersenhomeworks.R
import java.util.*

class CustomClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_SIZE = 300
        private const val STROKE_WIDTH = 10f

        private const val CLOCK_FACE_COLOR = Color.BLACK
        private const val DEFAULT_HAND_HOUR_COLOR = Color.DKGRAY
        private const val DEFAULT_HAND_MINUTE_COLOR = Color.BLUE
        private const val DEFAULT_HAND_SECOND_COLOR = Color.RED
        private const val DEFAULT_HAND_HOUR_LENGTH = DEFAULT_SIZE * 0.5f
        private const val DEFAULT_HAND_MINUTE_LENGTH = DEFAULT_SIZE * 0.6f
        private const val DEFAULT_HAND_SECOND_LENGTH = DEFAULT_SIZE * 0.7f
    }

    private var radius = (DEFAULT_SIZE - STROKE_WIDTH) / 2f
    private var centerX = DEFAULT_SIZE / 2f
    private var centerY = DEFAULT_SIZE / 2f
    private var hourIndexes = radius / 10f

    private var handHourColor = DEFAULT_HAND_HOUR_COLOR
    private var handMinuteColor = DEFAULT_HAND_MINUTE_COLOR
    private var handSecondColor = DEFAULT_HAND_SECOND_COLOR
    private var handHourLength = DEFAULT_SIZE * 0.5f
    private var handMinuteLength = DEFAULT_SIZE * 0.6f
    private var handSecondLength = DEFAULT_SIZE * 0.7f

    private val paint = Paint()


    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.CustomClockView)
            getAttributesFromXML(ta)
            ta.recycle()
        }

        paint.apply {
            isAntiAlias = true
            color = CLOCK_FACE_COLOR
            style = Paint.Style.STROKE
            strokeWidth = STROKE_WIDTH
        }
    }

    private fun getAttributesFromXML(ta: TypedArray) {
        handHourColor =
            ta.getColor(R.styleable.CustomClockView_hand_hour_color, DEFAULT_HAND_HOUR_COLOR)
        handMinuteColor =
            ta.getColor(R.styleable.CustomClockView_hand_minute_color, DEFAULT_HAND_MINUTE_COLOR)
        handSecondColor =
            ta.getColor(R.styleable.CustomClockView_hand_second_color, DEFAULT_HAND_SECOND_COLOR)
        handHourLength =
            ta.getDimension(R.styleable.CustomClockView_hand_hour_length, DEFAULT_HAND_HOUR_LENGTH)
        handMinuteLength =
            ta.getDimension(
                R.styleable.CustomClockView_hand_minute_length, DEFAULT_HAND_MINUTE_LENGTH
            )
        handSecondLength =
            ta.getDimension(
                R.styleable.CustomClockView_hand_second_length, DEFAULT_HAND_SECOND_LENGTH
            )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val size = resolveDefaultSize(widthMeasureSpec)
        Log.i("testing", size.toString())
        setMeasuredDimension(size, size)
    }

    private fun resolveDefaultSize(spec: Int): Int {
        return when (MeasureSpec.getMode(spec)) {
            MeasureSpec.UNSPECIFIED -> DEFAULT_SIZE
            else -> MeasureSpec.getSize(spec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        radius = (width - STROKE_WIDTH) / 2f
        centerX = width / 2f
        centerY = height / 2f
        hourIndexes = radius / 10f
        // handHourLength = radius * 0.5f
        // handMinuteLength = radius * 0.7f
        // handSecondLength = radius * 0.8f

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
            canvas.drawLine(0f, radius - hourIndexes, 0f, radius, paint)
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