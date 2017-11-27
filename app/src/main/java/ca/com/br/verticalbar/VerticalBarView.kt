package ca.com.br.verticalbar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.vertical_bar_view.view.*


class VerticalBarView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val barStrokeWidth = 1.dpToPx(context)
    private val barCornerRadius = 4f

    private var estimatedBarLayer: LayerDrawable? = null
    private var actualBarLayer: LayerDrawable? = null
    private var overBarLayer: LayerDrawable? = null
    private var estimatedBarDrawable: GradientDrawable? = null
    private var actualBarStroke: GradientDrawable? = null
    private var actualBarSolid: GradientDrawable? = null
    private var overBarStroke: GradientDrawable? = null
    private var overBarSolid: GradientDrawable? = null

    init {
        View.inflate(context, R.layout.vertical_bar_view, this)
        initDrawables()
    }

    fun bind(model: GraphModel, maxValue: Double, parentHeight: Int) {

        setLabels(icon = model.icon,
                description = model.description,
                estimatedValue = model.estimatedValue,
                value = model.value)

        val currentBarValue = model.value.coerceAtLeast(model.estimatedValue)
        val barSize = getBarSize(maxValue = maxValue,
                value = currentBarValue,
                parentHeight = parentHeight)
        val overValue = if (model.estimatedValue == 0.0) 0.0 else model.value - model.estimatedValue.coerceAtLeast(0.0)

        setupEstimatedBar(barSize = barSize, color = model.color)
        setupActualValueBar(value = model.value - overValue,
                currentBarValue = currentBarValue,
                barSize = barSize,
                color = model.color)
        setupOverBar(overValue = overValue,
                currentBarValue = currentBarValue,
                barSize = barSize,
                overColor = model.overColor)

        setupBackgrounds()

        redraw()

        estimatedBar.setOnClickListener { model.listener.invoke(model) }
    }

    private fun setupOverBar(overValue: Double, currentBarValue: Double, barSize: Int, overColor : Int) {

        val width: Int
        val actualBarRadii: FloatArray
        val strokeWidth: Int
        when {
            overValue > 0 -> {
                width = getHeightForValue(
                        value = overValue,
                        maxValue = currentBarValue,
                        height = barSize
                )
                actualBarRadii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
                strokeWidth = 0
                estimatedBarDrawable?.setStroke(barStrokeWidth, overColor)
                planningReferenceBar.visibility = View.VISIBLE
            }
            else -> {
                width = 0
                actualBarRadii = floatArrayOf(barCornerRadius, barCornerRadius, barCornerRadius,
                        barCornerRadius, 0f, 0f, 0f, 0f)
                strokeWidth = barStrokeWidth
                planningReferenceBar.visibility = View.GONE
            }
        }

        overBar.layoutParams.height = width
        actualBarSolid?.cornerRadii = actualBarRadii
        actualBarStroke?.cornerRadii = actualBarRadii
        overBarSolid?.setColor(overColor)
        overBarStroke?.setStroke(strokeWidth, overColor)
    }

    private fun setupActualValueBar(value: Double, currentBarValue: Double, barSize: Int, color: Int) {
        val actualHeight = getHeightForValue(
                value = value,
                maxValue = currentBarValue,
                height = barSize
        )
        actualBar.layoutParams.height = actualHeight
        actualBarSolid?.setColor(color)
        actualBarStroke?.setStroke(barStrokeWidth, color)
    }

    private fun setupEstimatedBar(barSize : Int, color : Int) {
        estimatedBar.layoutParams.height = barSize
        estimatedBarDrawable?.setStroke(barStrokeWidth, color)
        estimatedBarDrawable?.setColor(Color.TRANSPARENT)
    }

    private fun getBarSize(maxValue: Double, value: Double, parentHeight: Int) : Int {
        val max = Math.abs(maxValue)
        val currentValue = Math.abs(value)

        val estimatedValueLabelHeight = getEstimatedValueLabelHeight()
        val actualValueLabelHeight = getActualValueLabelHeight()
        val imageReferenceHeight = getImageReferenceHeight()
        val bottomLineHeight = getBottomLineHeight()

        val estimatedBarLP = estimatedBar.layoutParams as LinearLayout.LayoutParams
        val actualValueLP = actualValueLabel.layoutParams as LinearLayout.LayoutParams
        val imageReferenceLP = imageReference.layoutParams as RelativeLayout.LayoutParams

        val availableHeight = parentHeight -
                estimatedValueLabelHeight -
                actualValueLabelHeight -
                imageReferenceHeight -
                estimatedBarLP.topMargin -
                imageReferenceLP.topMargin -
                actualValueLP.topMargin -
                container.paddingTop -
                container.paddingBottom -
                bottomLineHeight

        return getHeightForValue(value = currentValue, maxValue = max, height = availableHeight)
    }


    private fun getHeightForValue(value: Double, maxValue: Double, height: Int): Int =
            ((value * height) / maxValue).toInt()

    private fun setLabels(icon: Int, estimatedValue: Double, value: Double, description: String) {

        imageReference.setImageResource(icon)
        txtDescription.text = description
        actualValueLabel.text = value.toString()

        if (estimatedValue > 0) {
            estimatedValueLabel.text = estimatedValue.toString()
        } else {
            estimatedValueLabel.visibility = View.INVISIBLE
        }
    }

    private fun initDrawables() {
        estimatedBarLayer = getEstimatedBarDrawable()
        actualBarLayer = getActualBarDrawable()
        overBarLayer = getOverBarDrawable()

        estimatedBarDrawable = estimatedBarLayer?.findDrawableByLayerId(R.id.stroke) as GradientDrawable
        actualBarStroke = actualBarLayer?.findDrawableByLayerId(R.id.strokeRect) as GradientDrawable
        actualBarSolid = actualBarLayer?.findDrawableByLayerId(R.id.solidRect) as GradientDrawable
        overBarStroke = overBarLayer?.findDrawableByLayerId(R.id.strokeRect) as GradientDrawable
        overBarSolid = overBarLayer?.findDrawableByLayerId(R.id.solidRect) as GradientDrawable
    }

    private fun getBottomLineHeight(): Int {
        bottomLine.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        return bottomLine.measuredHeight
    }

    private fun getImageReferenceHeight() : Int {
        imageReference.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        return imageReference.measuredHeight
    }

    private fun getActualValueLabelHeight() : Int {
        actualValueLabel.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        return actualValueLabel.measuredHeight
    }

    private fun getEstimatedValueLabelHeight(): Int {
        estimatedValueLabel.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        return estimatedValueLabel.measuredHeight
    }

    private fun getActualBarDrawable() = ContextCompat.getDrawable(context,
            R.drawable.vertical_bar_fill_drawable).mutate() as LayerDrawable

    private fun getEstimatedBarDrawable() = ContextCompat.getDrawable(context,
            R.drawable.vertical_bar_stroke_drawable).mutate() as LayerDrawable

    private fun getOverBarDrawable() = ContextCompat.getDrawable(context,
            R.drawable.over_vertical_bar_fill_drawable).mutate() as LayerDrawable

    private fun setupBackgrounds() {
        estimatedBarLayer?.setDrawableByLayerId(R.id.stroke, estimatedBarDrawable)
        actualBarLayer?.setDrawableByLayerId(R.id.stroke, actualBarStroke)
        actualBarLayer?.setDrawableByLayerId(R.id.solidRect, actualBarSolid)
        overBarLayer?.setDrawableByLayerId(R.id.stroke, overBarStroke)
        overBarLayer?.setDrawableByLayerId(R.id.solidRect, overBarSolid)

        ViewCompat.setBackground(estimatedBar, estimatedBarLayer)
        ViewCompat.setBackground(actualBar, actualBarLayer)
        ViewCompat.setBackground(overBar, overBarLayer)
    }

    private fun redraw() {
        estimatedBar.invalidate()
        estimatedBar.requestLayout()
        actualBar.invalidate()
        actualBar.requestLayout()
        overBar.invalidate()
        overBar.requestLayout()
        invalidate()
        requestLayout()
    }

}