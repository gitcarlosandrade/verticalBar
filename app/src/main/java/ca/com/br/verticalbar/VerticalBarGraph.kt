package ca.com.br.verticalbar

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.vertical_bar_graph.view.*

class VerticalBarGraph : LinearLayout{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init{
        View.inflate(context, R.layout.vertical_bar_graph, this)
    }

    fun bind(graphModel: List<GraphModel>) {

        if (graphModel.isEmpty()) {
            IllegalStateException("GraphModel list cannot be empty. ")
        }

        val barModel = graphModel.maxBy { Math.max(it.value, it.estimatedValue) }

        barModel?.let {

            val max = Math.max(it.value, it.estimatedValue)

            graphModel.map { model ->

                val verticalBarView = VerticalBarView(context)

                val parent = containerHsvGraph.parent as LinearLayout
                val parentHeight = parent.layoutParams.height

                verticalBarView.bind(value = model.value,
                        maxValue = max,
                        estimatedValue = model.estimatedValue,
                        color = model.color,
                        overColor = model.overColor,
                        icon = model.icon,
                        description = model.description,
                        parentHeight = parentHeight)

                hsvChild.addView(verticalBarView)
            }
        }
    }
}