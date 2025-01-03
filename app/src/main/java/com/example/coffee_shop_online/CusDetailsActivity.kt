package com.example.coffee_shop_online

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CusDetailsActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var address: EditText
    private lateinit var location: EditText
    private lateinit var phone: EditText
    private lateinit var btnnext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cusdetails)

        // Initialize the views
        name = findViewById(R.id.name)
        address = findViewById(R.id.address)
        location = findViewById(R.id.location)
        phone = findViewById(R.id.phone)
        btnnext = findViewById(R.id.btnnext)

        // Initialize Firestore
        val firestore = FirebaseFirestore.getInstance()

        btnnext.setOnClickListener {
            val nameText = name.text.toString().trim()
            val addressText = address.text.toString().trim()
            val locationText = location.text.toString().trim()
            val phoneText = phone.text.toString().trim()

            // Validate input fields
            if (nameText.isEmpty()) {
                name.error = "Username Required!"
                return@setOnClickListener
            } else if (addressText.isEmpty()) {
                address.error = "Address Required!"
                return@setOnClickListener
            } else if (locationText.isEmpty()) {
                location.error = "Location Required!"
                return@setOnClickListener
            } else if (phoneText.isEmpty()) {
                phone.error = "Phone Number Required!"
                return@setOnClickListener
            } else {
                // Create a map with customer details
                val customer = hashMapOf(
                    "name" to nameText,
                    "address" to addressText,
                    "location" to locationText,
                    "phone" to phoneText
                )

                firestore.collection("customers")
                    .add(customer)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Details Saved Successfully!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }

            }
        }
    }
}