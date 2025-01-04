package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class Payment : AppCompatActivity() {

    private lateinit var cardNumberEdit: EditText
    private lateinit var holderNameEdit: EditText
    private lateinit var cvvEdit: EditText
    private lateinit var exDate: EditText
    private lateinit var payNowbtn: Button

    private var orderId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        cardNumberEdit = findViewById(R.id.card_number)
        holderNameEdit = findViewById(R.id.cardHolder)
        cvvEdit = findViewById(R.id.cvv)
        exDate = findViewById(R.id.exp_date)
        payNowbtn = findViewById(R.id.btn_paynow)

        // Retrieve the orderId from the intent
        orderId = intent.getStringExtra("orderId")

        payNowbtn.setOnClickListener {
            if (cardNumberEdit.text.toString().isEmpty() || holderNameEdit.text.toString().isEmpty() ||
                cvvEdit.text.toString().isEmpty() || exDate.text.toString().isEmpty()
            ) {
                Toast.makeText(this@Payment, "Please enter all details", Toast.LENGTH_LONG).show()
            } else {
                saveDataToFirebaseDatabase(
                    cardNumberEdit.text.toString(),
                    holderNameEdit.text.toString(),
                    cvvEdit.text.toString(),
                    exDate.text.toString()
                )
            }
        }
    }

    private fun saveDataToFirebaseDatabase(cardNumber: String, holderName: String, cvv: String, ExDate: String) {
        if (orderId == null) {
            Toast.makeText(this, "Order ID is missing. Cannot save payment.", Toast.LENGTH_LONG).show()
            return
        }

        val paymentInfo = PaymentInfo(orderId!!, cardNumber, holderName, cvv, ExDate)
        val dbRef = FirebaseDatabase.getInstance().getReference("PaymentInfo")
        dbRef.push().setValue(paymentInfo)
            .addOnSuccessListener {
                Toast.makeText(this, "Payment data saved successfully", Toast.LENGTH_LONG).show()
                // Clear input fields
                cardNumberEdit.setText("")
                holderNameEdit.setText("")
                cvvEdit.setText("")
                exDate.setText("")

                val intent = Intent(this, CategoryActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Failed to save data: ${error.message}", Toast.LENGTH_LONG).show()
            }
    }
}
