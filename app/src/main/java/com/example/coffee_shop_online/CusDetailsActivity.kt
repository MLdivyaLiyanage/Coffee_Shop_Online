package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class CusDetailsActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var address: EditText
    private lateinit var phone: EditText
    private lateinit var btnNext: Button

    private var orderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cusdetails)

        // Initialize UI components
        name = findViewById(R.id.name)
        address = findViewById(R.id.address)
        phone = findViewById(R.id.phone)
        btnNext = findViewById(R.id.btnnext)

        // Retrieve the orderId from the intent
        orderId = intent.getStringExtra("orderId")

        // Set up the "Next" button click listener
        btnNext.setOnClickListener {
            val customerName = name.text.toString().trim()
            val customerAddress = address.text.toString().trim()
            val customerPhone = phone.text.toString().trim()

            if (customerName.isEmpty() || customerAddress.isEmpty() || customerPhone.isEmpty()) {
                Toast.makeText(
                    this@CusDetailsActivity,
                    "Please enter all details",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                saveDataToFirebaseDatabase(customerName, customerAddress, customerPhone)
            }
        }
    }

    private fun saveDataToFirebaseDatabase(name: String, address: String, phone: String) {
        if (orderId == null) {
            Toast.makeText(this, "Order ID is missing. Cannot save payment.", Toast.LENGTH_LONG).show()
            return
        }

        // Create the customer info object
        val customerInfo = CustomerInfo(orderId!!, name, address, phone)
        val dbRef = FirebaseDatabase.getInstance().getReference("CustomerInfo")

        // Save data to Firebase with orderId as the key
        dbRef.child(orderId!!).setValue(customerInfo)
            .addOnSuccessListener {
                Toast.makeText(this, "Customer data saved successfully", Toast.LENGTH_LONG).show()

                // Navigate to the Payment page
                val intent = Intent(this, Payment::class.java)
                intent.putExtra("orderId", orderId) // Pass the orderId to the PaymentActivity
                startActivity(intent)

                // Clear input fields
                this.name.setText("") // Explicitly clear the EditText fields
                this.address.setText("")
                this.phone.setText("")
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Failed to save data: ${error.message}", Toast.LENGTH_LONG).show()
            }
    }
}
