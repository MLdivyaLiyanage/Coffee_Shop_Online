package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DescIcedEspresso : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_desc_iced_espresso)

        val orderNow : Button = findViewById(R.id.orderNow)
        val reviewBtn : Button = findViewById(R.id.customerReviews)

        /*orderNow.setOnClickListener {
            //Go to DescActivity when click the arrow
            val intent = Intent(this, CustomerDetails::class.java)
            startActivity(intent)
        }

        reviewBtn.setOnClickListener {
            //Go to DescActivity when click the arrow
            val intent = Intent(this, Review::class.java)
            startActivity(intent)*/
        }
    }
