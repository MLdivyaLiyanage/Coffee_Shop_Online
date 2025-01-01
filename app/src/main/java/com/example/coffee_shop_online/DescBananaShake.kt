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

class DescBananaShake : AppCompatActivity() {

    private lateinit var lBananaShake: Button
    private lateinit var mBananaShake: Button
    private lateinit var sBananaShake: Button
    private lateinit var quantityEdt: EditText
    private lateinit var branchesEdt: Spinner
    private lateinit var orderBtn: Button
    private lateinit var mapButton: Button

    private lateinit var database: DatabaseReference

    private var selectedSize: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc_banana_shake)

        database = FirebaseDatabase.getInstance().reference

        lBananaShake = findViewById(R.id.lBananaShake)
        mBananaShake = findViewById(R.id.mBananaShake)
        sBananaShake = findViewById(R.id.sBananaShake)
        quantityEdt = findViewById(R.id.Quantity)
        branchesEdt = findViewById(R.id.my_spinner)
        orderBtn = findViewById(R.id.orderNow)
        mapButton = findViewById(R.id.mapButton)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.branch_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        branchesEdt.adapter = adapter

        lBananaShake.setOnClickListener { selectSize("Large", lBananaShake) }
        mBananaShake.setOnClickListener { selectSize("Medium", mBananaShake) }
        sBananaShake.setOnClickListener { selectSize("Small", sBananaShake) }

        orderBtn.setOnClickListener { processOrder() }

        mapButton.setOnClickListener {
            val intent = Intent(this, BranchLocations::class.java)
            startActivity(intent)
        }
    }

    private fun selectSize(size: String, selectedButton: Button) {
        selectedSize = size
        resetButtonColors()
        selectedButton.setBackgroundColor(Color.parseColor("#D2691E"))
    }

    private fun resetButtonColors() {
        lBananaShake.setBackgroundColor(Color.parseColor("#8B4513"))
        mBananaShake.setBackgroundColor(Color.parseColor("#8B4513"))
        sBananaShake.setBackgroundColor(Color.parseColor("#8B4513"))
    }

    private fun processOrder() {
        val quantity = quantityEdt.text.toString().trim()
        val branch = branchesEdt.selectedItem.toString()

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

        database.child("Order").child(orderId).setValue(order)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("orderId", orderId)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Failed to place the order. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    data class Order(val orderId: String, val size: String, val quantity: String, val branch: String)
}
