package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Category2 : AppCompatActivity(){

    private val img = arrayOf(
        R.drawable.icemilk, R.drawable.skinnyicecocktail, R.drawable.michelada,
        R.drawable.stawberryslush, R.drawable.goldenginger)

    private val texts = arrayOf("Iced Milk","Skinny Ice Cocktail","Michelada","Strawberry Slush","Golden Ginger")
    private val price = arrayOf("Rs.850","Rs.1000","Rs.700","Rs.990","Rs.670")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category2)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdaptor2(img,texts,price)

        val back : ImageView = findViewById(R.id.back_button)

        back.setOnClickListener(){
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}