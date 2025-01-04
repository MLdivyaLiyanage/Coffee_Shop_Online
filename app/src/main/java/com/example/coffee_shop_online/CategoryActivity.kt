package com.example.coffee_shop_online


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class CategoryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_category)

        val cate1: CardView = findViewById(R.id.card_ice_coffee)
        val cate2: CardView = findViewById(R.id.card_ice_drink)
        val cate3: CardView = findViewById(R.id.shake)
        val cate4: CardView = findViewById(R.id.cake)
        val cate5: CardView = findViewById(R.id.foods)
        val cate6: CardView = findViewById(R.id.restaurant)

        //Click listener on the next
        cate1.setOnClickListener() {
            //Go to Category1 Activity when click the card
            val intent = Intent(this, Category1::class.java)
            startActivity(intent)
        }
        cate2.setOnClickListener() {
            val intent = Intent(this, Category2::class.java)
            startActivity(intent)
        }
        cate3.setOnClickListener() {
            val intent = Intent(this, Category3::class.java)
            startActivity(intent)
        }
        cate4.setOnClickListener() {
            val intent = Intent(this, Category4::class.java)
            startActivity(intent)
        }
        cate5.setOnClickListener() {
            val intent = Intent(this, Category5::class.java)
            startActivity(intent)
        }
        cate6.setOnClickListener() {
            val intent = Intent(this, Branch::class.java)
            startActivity(intent)
        }
    }
}
