package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Category4 : AppCompatActivity(){

    private val img = arrayOf(
        R.drawable.yellowbutter, R.drawable.redvelvet, R.drawable.chocolate,
        R.drawable.vanilla, R.drawable.white,R.drawable.devilsfood)

    private val texts = arrayOf("Yellow Butter Cake","Red Velvet Cake","Chocolate Cake",
        "Vanilla Cake","White Cake","Devil's Food Cake")
    private val price = arrayOf("Rs.850","Rs.1000","Rs.700","Rs.990","Rs.670","Rs.900")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category4)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdaptor4(img,texts,price)

        val back : ImageView = findViewById(R.id.back_button)

        back.setOnClickListener(){
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}