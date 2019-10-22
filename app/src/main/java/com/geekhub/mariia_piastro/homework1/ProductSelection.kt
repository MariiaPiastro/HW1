package com.geekhub.mariia_piastro.homework1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ProductSelection : AppCompatActivity(), View.OnClickListener {

    lateinit var textViewDress: TextView
    lateinit var textViewTShirt: TextView
    lateinit var textViewShorts: TextView
    lateinit var textViewOrderTitle: TextView
    lateinit var textViewOrder: TextView
    lateinit var textViewOrderColor: TextView
    lateinit var buttonSendOrder: Button
    private var product = ""
    private var color = ""
    var message: String = ""
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_selection)
        supportActionBar?.hide()

        textViewDress = findViewById(R.id.tvDress)
        textViewTShirt = findViewById(R.id.tvTShirt)
        textViewShorts = findViewById(R.id.tvShorts)
        textViewOrderTitle = findViewById(R.id.tvOrderTitle)
        textViewOrder = findViewById(R.id.tvOrder)
        textViewOrderColor = findViewById(R.id.tvOrderColor)
        buttonSendOrder = findViewById(R.id.buttonSendOrder)

        savedInstanceState?.run {
            color = getString("color") ?: ""
            product = getString("product") ?: ""
            if (color.isNotEmpty() && product.isNotEmpty()) {
                orderResult(product, color)
            }
        }


        textViewDress.setOnClickListener(this)
        textViewTShirt.setOnClickListener(this)
        textViewShorts.setOnClickListener(this)
        buttonSendOrder.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putString("product", product)
            putString("color", color)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvDress -> {
                product = "Платье"
                openColorSelectionActivity(product)
            }
            R.id.tvTShirt -> {
                product = "Футболка"
                openColorSelectionActivity(product)
            }
            R.id.tvShorts -> {
                product = "Шорты"
                openColorSelectionActivity(product)
            }
            R.id.buttonSendOrder -> {
                sendOrder(product, color)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            color = data?.getStringExtra("color").toString()
            orderResult(product, color)
        }
    }

    private fun orderResult(product: String, color: String) {
        textViewOrderTitle.text = "Ваш заказ"
        textViewOrder.text = String.format("%s", product)
        textViewOrderColor.text = String.format("Цвет: %s", color)
    }

    private fun sendOrder(product: String, color: String) {
        if (product.isNotEmpty() && color.isNotEmpty()) {
            message = String.format("%s\nЦвет: %s", product, color)

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_TEXT, message)
                putExtra(Intent.EXTRA_SUBJECT, "Ваш заказ")
            }
            val chooser = Intent.createChooser(intent, "Выберите приложение")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        } else {
            Toast.makeText(this, "Выберите параметры заказа!", Toast.LENGTH_LONG).show()
        }
    }

    private fun openColorSelectionActivity(product: String) {
        val intent = Intent(this, ColorSelection::class.java)
        intent.putExtra("product", product)
        startActivityForResult(intent, REQUEST_CODE)
    }
}




