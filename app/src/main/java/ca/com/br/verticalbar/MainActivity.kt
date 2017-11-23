package ca.com.br.verticalbar

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val maxValue = 4000.00
        verticalBar1.bind(
                value = 2000.00,
                maxValue = maxValue,
                estimatedValue = maxValue,
                color = ContextCompat.getColor(this, R.color.blue),
                overColor = Color.BLUE,
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)

        verticalBar2.bind(
                value = 1200.0,
                maxValue = maxValue,
                estimatedValue = 1000.0,
                color = ContextCompat.getColor(this, R.color.green),
                overColor = ContextCompat.getColor(this, R.color.dkgreen),
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)

        verticalBar3.bind(
                value = 600.0,
                maxValue = maxValue,
                estimatedValue = 700.0,
                color = ContextCompat.getColor(this, R.color.orange),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)

        verticalBar4.bind(
                value = 500.0,
                maxValue = maxValue,
                estimatedValue = 0.0,
                color = ContextCompat.getColor(this, R.color.purple),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)



        verticalBar5.bind(
                value = 2000.00,
                maxValue = maxValue,
                estimatedValue = maxValue,
                color = ContextCompat.getColor(this, R.color.blue),
                overColor = Color.BLUE,
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)

        verticalBar6.bind(
                value = 1200.0,
                maxValue = maxValue,
                estimatedValue = 1000.0,
                color = ContextCompat.getColor(this, R.color.green),
                overColor = ContextCompat.getColor(this, R.color.dkgreen),
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)

        verticalBar7.bind(
                value = 600.0,
                maxValue = maxValue,
                estimatedValue = 700.0,
                color = ContextCompat.getColor(this, R.color.orange),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)

        verticalBar8.bind(
                value = 500.0,
                maxValue = maxValue,
                estimatedValue = 0.0,
                color = ContextCompat.getColor(this, R.color.purple),
                overColor = Color.RED,
                icon = R.drawable.ic_add_green_10dp,
                parentHeight = barContainer.layoutParams.height)
    }
}
