package com.example.coffee_shop_online

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DescColdBrewCoffee : AppCompatActivity() {

    private lateinit var lBCoffee: Button
    private lateinit var mBCoffee: Button
    private lateinit var sBCoffee: Button
    private lateinit var quantityEdt: EditText
    private lateinit var branchesEdt: Spinner
    private lateinit var orderBtn: Button

    private lateinit var database: DatabaseReference

    // Variable to store the selected size
    private var selectedSize: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc_cold_brew_coffee)

        // Initialize Firebase Database reference
        database = FirebaseDatabase.getInstance().reference

        // Initialize views
        lBCoffee = findViewById(R.id.lPremiumCapp)
        mBCoffee = findViewById(R.id.mPremiumCapp)
        sBCoffee = findViewById(R.id.sPremiumCapp)
        quantityEdt = findViewById(R.id.Quantity)
        branchesEdt = findViewById(R.id.my_spinner)
        orderBtn = findViewById(R.id.orderNow)

        // Set up the spinner with branch names
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.branch_list, // Array defined in res/values/strings.xml
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        branchesEdt.adapter = adapter

        // Assign click listeners for size buttons
        lBCoffee.setOnClickListener { selectSize("Large", lBCoffee) }
        mBCoffee.setOnClickListener { selectSize("Medium", mBCoffee) }
        sBCoffee.setOnClickListener { selectSize("Small", sBCoffee) }

        // Handle Order button click
        orderBtn.setOnClickListener {
            processOrder()
        }
    }

    private fun selectSize(size: String, selectedButton: Button) {
        // Store the selected size
        selectedSize = size

        // Reset button colors and highlight the selected one
        resetButtonColors()
        selectedButton.setBackgroundColor(Color.parseColor("#D2691E"))
    }

    private fun resetButtonColors() {
        lBCoffee.setBackgroundColor(Color.parseColor("#8B4513"))
        mBCoffee.setBackgroundColor(Color.parseColor("#8B4513"))
        sBCoffee.setBackgroundColor(Color.parseColor("#8B4513"))
    }

    private fun processOrder() {
        val quantity = quantityEdt.text.toString().trim()
        val branch = branchesEdt.selectedItem.toString()

        // Validate inputs
        if (quantity.isEmpty()) {
            Toast.makeText(this, "Please enter a quantity.", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedSize == null) {
            Toast.makeText(this, "Please select a size.", Toast.LENGTH_SHORT).show()
            return
        }

        val orderId = database.child("Order").push().key ?: run {
            Toast.makeText(this, "Failed to generate order ID.", Toast.LENGTH_SHORT).show()
            return
        }

        val order = Order(orderId, selectedSize!!, quantity, branch)

        // Store order in Firebase
        database.child("Order").child(orderId).setValue(order)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("orderId", orderId) // Pass orderId to the next activity if needed
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Failed to place the order. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    // Data class to represent the order
    data class Order(val orderId: String, val size: String, val quantity: String, val branch: String)
}