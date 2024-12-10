package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Category5 : AppCompatActivity(){

    private val img = arrayOf(
        R.drawable.chicken, R.drawable.pizza, R.drawable.burger,
        R.drawable.pasta, R.drawable.noodles,R.drawable.waffeles)

    private val texts = arrayOf("Crispy Chicken","Pizza","Burger",
        "Pasta","Noodles","Waffeles")
    private val price = arrayOf("Rs.850","Rs.1000","Rs.700","Rs.990","Rs.670","Rs.900")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category5)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdaptor5(img,texts,price)

        val back : ImageView = findViewById(R.id.back_button)

        back.setOnClickListener(){
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }
}