package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrowIcon: ImageView = findViewById(R.id.arrow_icon)

        //click listener on the next
        arrowIcon.setOnClickListener {
            //Go to DescActivity when click the arrow
            val intent = Intent(this, DescAvocadoShake::class.java)
            startActivity(intent)
        }
    }
}
