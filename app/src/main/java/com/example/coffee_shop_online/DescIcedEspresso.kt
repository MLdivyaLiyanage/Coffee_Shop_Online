package com.example.coffee_shop_online

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DescIcedEspresso : AppCompatActivity() {

    private lateinit var lPremiumCapp: Button
    private lateinit var mPremiumCapp: Button
    private lateinit var sPremiumCapp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_desc_iced_espresso)

        val spinner: Spinner = findViewById(R.id.my_spinner)
        lPremiumCapp = findViewById(R.id.lPremiumCapp)
        mPremiumCapp = findViewById(R.id.mPremiumCapp)
        sPremiumCapp = findViewById(R.id.sPremiumCapp)

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.branch_list, // This array must be defined in res/values/strings.xml
            android.R.layout.simple_spinner_item
        )

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        spinner.adapter = adapter

        // Handle coffee size button clicks
        val sizeClickListener = View.OnClickListener { view ->
            resetButtonColors() // Reset all button colors
            view.setBackgroundColor(Color.parseColor("#D2691E")) // Highlight the clicked button
        }

        // Assign click listeners to each size button
        lPremiumCapp.setOnClickListener(sizeClickListener)
        mPremiumCapp.setOnClickListener(sizeClickListener)
        sPremiumCapp.setOnClickListener(sizeClickListener)

        /* Uncomment and set up button click listeners for future navigation
        val orderNow: Button = findViewById(R.id.orderNow)
        val reviewBtn: Button = findViewById(R.id.customerReviews)

        orderNow.setOnClickListener {
            // Navigate to CustomerDetails activity
            val intent = Intent(this, CustomerDetails::class.java)
            startActivity(intent)
        }

        reviewBtn.setOnClickListener {
            // Navigate to Review activity
            val intent = Intent(this, Review::class.java)
            startActivity(intent)
        }
        */
    }

    private fun resetButtonColors() {
        // Reset buttons to their default colors
        lPremiumCapp.setBackgroundColor(Color.parseColor("#8B4513")) // Default color
        mPremiumCapp.setBackgroundColor(Color.parseColor("#8B4513"))
        sPremiumCapp.setBackgroundColor(Color.parseColor("#8B4513"))
    }
}
