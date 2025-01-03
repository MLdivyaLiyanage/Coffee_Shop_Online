package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Category3 : AppCompatActivity(){

    private val img = arrayOf(
        R.drawable.milkshake, R.drawable.chocolatebanana, R.drawable.strawberry,
        R.drawable.icedmochafusion, R.drawable.cafelattemilk,R.drawable.avacado)

    private val texts = arrayOf("Oreo Milk Shake","Chocolate Banana Shake","Yummy Strawberry Shake",
        "Iced Mocha Fusion Shake","Cafe Latte Milk Shake","Simple Avacado Milk Shake")
    private val price = arrayOf("Rs.850","Rs.1000","Rs.700","Rs.990","Rs.670","RS.900")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category3)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdaptor3(img,texts,price)

        val back : ImageView = findViewById(R.id.back_button)

        back.setOnClickListener(){
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}