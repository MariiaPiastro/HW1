package com.geekhub.mariia_piastro.homework1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class ColorSelection : AppCompatActivity(), View.OnClickListener {

    lateinit var textViewRed: TextView
    lateinit var textViewGreen: TextView
    lateinit var textViewBlue: TextView
    lateinit var textViewYellow: TextView
    lateinit var textViewSelectedProduct: TextView
    lateinit var color: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_selection)
        supportActionBar?.hide()

        textViewBlue = findViewById(R.id.tvBlue)
        textViewGreen = findViewById(R.id.tvGreen)
        textViewRed = findViewById(R.id.tvRed)
        textViewYellow = findViewById(R.id.tvYellow)
        textViewSelectedProduct = findViewById(R.id.tvSelectedProduct)

        val product = intent?.getStringExtra("product")
        textViewSelectedProduct.text = getString(R.string.selected_product, product)

        textViewRed.setOnClickListener(this)
        textViewYellow.setOnClickListener(this)
        textViewGreen.setOnClickListener(this)
        textViewBlue.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvRed -> {
                color = "Красный"
            }
            R.id.tvGreen -> {
                color = "Зеленый"
            }
            R.id.tvYellow -> {
                color = "Желтый"
            }
            R.id.tvBlue -> {
                color = "Синий"
            }
        }
        setResult(Activity.RESULT_OK, Intent().putExtra("color", color))
        finish()
    }
}
