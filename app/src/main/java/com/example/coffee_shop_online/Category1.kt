package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffee_shop_online.CustomAdaptor


class Category1 : AppCompatActivity(){

    private val img = arrayOf(
        R.drawable.icedespresso, R.drawable.icedmocha, R.drawable.coldbrewcoffee,
        R.drawable.shakerato, R.drawable.icedlatte, R.drawable.mazagran)

    private val texts = arrayOf("Iced Espresso","Iced Mocha","Cold Brew Coffee","Shakerato","Iced Latte","Mazagram")
    private val price = arrayOf("Rs.850","Rs.1000","Rs.700","Rs.990","Rs.670","Rs.580")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category1)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdaptor(img,texts,price)

        val back : ImageView = findViewById(R.id.back_button)

        back.setOnClickListener(){
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }

    }

}