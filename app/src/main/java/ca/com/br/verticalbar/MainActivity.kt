package ca.com.br.verticalbar

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        verticalBarGraph.bind(getList())
    }


    private fun getList() : List<GraphModel>{

        val graphModel1 = GraphModel(
                value = 2000.00,
                estimatedValue = 2500.00,
                color = ContextCompat.getColor(this, R.color.blue),
                overColor = Color.BLUE,
                icon = R.drawable.ic_add_green_10dp)

        val graphModel2 = GraphModel(
                value = 1200.0,
                estimatedValue = 1000.0,
                color = ContextCompat.getColor(this, R.color.green),
                overColor = ContextCompat.getColor(this, R.color.dkgreen),
                icon = R.drawable.ic_add_green_10dp)

        val graphModel3 = GraphModel(
                value = 600.0,
                estimatedValue = 700.0,
                color = ContextCompat.getColor(this, R.color.orange),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp)

        val graphModel4 = GraphModel(
                value = 500.0,
                estimatedValue = 0.0,
                color = ContextCompat.getColor(this, R.color.purple),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp)

        val graphModel5 = GraphModel(
                value = 2000.00,
                estimatedValue = 2000.00,
                color = ContextCompat.getColor(this, R.color.blue),
                overColor = Color.BLUE,
                icon = R.drawable.ic_add_green_10dp)

        val graphModel6 = GraphModel(
                value = 1300.0,
                estimatedValue = 500.0,
                color = ContextCompat.getColor(this, R.color.green),
                overColor = ContextCompat.getColor(this, R.color.dkgreen),
                icon = R.drawable.ic_add_green_10dp)

        val graphModel7 = GraphModel(
                value = 600.0,
                estimatedValue = 0.0,
                color = ContextCompat.getColor(this, R.color.orange),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp)

        val graphModel8 = GraphModel(
                value = 3000.0,
                estimatedValue = 0.0,
                color = ContextCompat.getColor(this, R.color.purple),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp)

        return arrayListOf(graphModel1, graphModel2, graphModel3, graphModel4, graphModel5, graphModel6, graphModel7, graphModel8)
    }
}
