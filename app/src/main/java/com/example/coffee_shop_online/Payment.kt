package com.example.coffee_shop_online

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Payment : AppCompatActivity(){

    lateinit var cardNumberEdit:EditText
    lateinit var holderNameEdit:EditText
    lateinit var cvvEdit:EditText
    lateinit var exDate:EditText
    lateinit var payNowbtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        cardNumberEdit = findViewById(R.id.card_number)
        holderNameEdit = findViewById(R.id.cardHolder)
        cvvEdit = findViewById(R.id.cvv)
        exDate = findViewById(R.id.exp_date)
        payNowbtn = findViewById(R.id.btn_paynow)

        payNowbtn.setOnClickListener {
            if (cardNumberEdit.text.toString().isEmpty() || holderNameEdit.text.toString().isEmpty() ||
                cvvEdit.text.toString().isEmpty() || exDate.text.toString().isEmpty())
            {
                Toast.makeText(this@Payment,"Please enter all details",Toast.LENGTH_LONG).show()
            }
            else
            {
                saveDataToFirebaseDatabase(cardNumberEdit.text.toString(),holderNameEdit.text.toString(),
                    cvvEdit.text.toString(),exDate.text.toString())
            }
        }
    }

    private fun saveDataToFirebaseDatabase(cardNumber: String, holderName: String, cvv: String, ExDate: String)
    {
        val paymentInfo = PaymentInfo(cardNumber,holderName,cvv,ExDate)
        val dbRef = FirebaseDatabase.getInstance().getReference("PaymentInfo")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dbRef.setValue(paymentInfo)
                Toast.makeText(this@Payment,"Data saved Successfully", Toast.LENGTH_LONG).show()
                cardNumberEdit.setText("")
                holderNameEdit.setText("")
                cvvEdit.setText("")
                exDate.setText("")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Payment,"Fail to save data : ${error}", Toast.LENGTH_LONG).show()
                cardNumberEdit.setText("")
                holderNameEdit.setText("")
                cvvEdit.setText("")
                exDate.setText("")
            }

        })
    }
}