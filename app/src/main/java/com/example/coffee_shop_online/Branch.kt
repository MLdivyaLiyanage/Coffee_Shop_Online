package com.example.coffee_shop_online


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class Branch : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_branch)

        val branch1: CardView = findViewById(R.id.card_galle_karapitiya)
        val branch2: CardView = findViewById(R.id.card_galle_busstand)
        val branch3: CardView = findViewById(R.id.card_galle_wakwella)
        val branch4: CardView = findViewById(R.id.card_galle_magalle)
        val branch5: CardView = findViewById(R.id.card_mathara)
        val branch6: CardView = findViewById(R.id.card_mathara_nupe)
        val branch7: CardView = findViewById(R.id.card_mathara_beachroad)
        val branch8: CardView = findViewById(R.id.card_mathara_kotuwegoda)

        branch1.setOnClickListener() {
            val intent = Intent(this, Category1::class.java)
            startActivity(intent)
        }
        branch2.setOnClickListener() {
            val intent = Intent(this, Category2::class.java)
            startActivity(intent)
        }
        branch3.setOnClickListener() {
            val intent = Intent(this, Category3::class.java)
            startActivity(intent)
        }
        branch4.setOnClickListener() {
            val intent = Intent(this, Category4::class.java)
            startActivity(intent)
        }
        branch5.setOnClickListener() {
            val intent = Intent(this, Category5::class.java)
            startActivity(intent)
        }
        branch6.setOnClickListener() {
            val intent = Intent(this, Category5::class.java)
            startActivity(intent)
        }
        branch7.setOnClickListener() {
            val intent = Intent(this, Category5::class.java)
            startActivity(intent)
        }
        branch8.setOnClickListener() {
            val intent = Intent(this, Category5::class.java)
            startActivity(intent)
        }
    }
}
