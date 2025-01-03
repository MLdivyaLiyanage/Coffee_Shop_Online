package com.example.coffee_shop_online

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class tableBooking : AppCompatActivity() {

    private lateinit var display: TextView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var btnNext: Button

    private val tableStatus = mutableMapOf(
        "Table 1" to false,
        "Table 2" to false,
        "Table 3" to false,
        "Table 4" to false,
        "Table 5" to false,
        "Table 6" to false,
        "Table 7" to false,
        "Table 8" to false
    )

    private val bookingHistory = StringBuilder()
    private var selectedBranch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_booking)

        databaseRef = FirebaseDatabase.getInstance().getReference("BookingHistoryKarapitiya")

        // Get the branch name passed from the Branch activity
        selectedBranch = intent.getStringExtra("branchName")

        display = findViewById(R.id.display)
        btnNext = findViewById(R.id.btnNext)

        val buttons = listOf(
            Pair(findViewById(R.id.button1), "Table 1"),
            Pair(findViewById(R.id.button2), "Table 2"),
            Pair(findViewById(R.id.button3), "Table 3"),
            Pair(findViewById(R.id.button4), "Table 4"),
            Pair(findViewById(R.id.button5), "Table 5"),
            Pair(findViewById(R.id.button6), "Table 6"),
            Pair(findViewById(R.id.button7), "Table 7"),
            Pair(findViewById<Button>(R.id.button8), "Table 8")
        )

        // Fetch and display the current booking status from Firebase
        fetchTableStatus()

        // Set button listeners
        setButtonListeners(buttons)

        btnNext.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchTableStatus() {
        // Retrieve table booking history from Firebase
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Reset table status
                tableStatus.forEach { (key, _) -> tableStatus[key] = false }

                // Iterate over booking history and update table statuses
                for (data in snapshot.children) {
                    val tableName = data.child("tableName").getValue(String::class.java)
                    val isBooked = data.child("message").getValue(String::class.java)?.contains("booked", true) == true

                    if (tableName != null) {
                        tableStatus[tableName] = isBooked
                    }
                }

                // Update button text based on booking status
                updateTableButtons()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@tableBooking, "Error fetching data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateTableButtons() {
        val buttons = listOf(
            Pair(findViewById(R.id.button1), "Table 1"),
            Pair(findViewById(R.id.button2), "Table 2"),
            Pair(findViewById(R.id.button3), "Table 3"),
            Pair(findViewById(R.id.button4), "Table 4"),
            Pair(findViewById(R.id.button5), "Table 5"),
            Pair(findViewById(R.id.button6), "Table 6"),
            Pair(findViewById(R.id.button7), "Table 7"),
            Pair(findViewById<Button>(R.id.button8), "Table 8")
        )

        for ((button, tableName) in buttons) {
            val isBooked = tableStatus[tableName] ?: false
            button.text = if (isBooked) {
                "$tableName (Booked)"
            } else {
                "$tableName (Available)"
            }
        }
    }

    private fun setButtonListeners(buttons: List<Pair<Button, String>>) {
        for ((button, tableName) in buttons) {
            button.setOnClickListener {
                handleTableBooking(button, tableName)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun handleTableBooking(button: Button, tableName: String) {
        val isBooked = tableStatus[tableName] ?: false

        // Check if the table is already booked
        if (isBooked) {
            // If the table is booked, show a message and return
            Toast.makeText(this, "$tableName is already booked", Toast.LENGTH_SHORT).show()
            return
        }

        val nameField = findViewById<TextView>(R.id.TextName)
        val phoneField = findViewById<TextView>(R.id.TextPhoneNo)

        val name = nameField.text.toString().trim()
        val phone = phoneField.text.toString().trim()

        // Validate the input fields
        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please enter your name and phone number", Toast.LENGTH_SHORT).show()
            return
        }

        // If the table is available, proceed with booking
        val message = "$tableName is now booked"
        tableStatus[tableName] = true
        button.text = "$tableName (Booked)"

        bookingHistory.append(message).append("\n")
        display.text = bookingHistory.toString()

        // Save the booking to Firebase
        saveToDatabase(message, tableName, name, phone)
    }

    private fun saveToDatabase(message: String, tableName: String, name: String, phone: String) {
        val historyId = databaseRef.push().key ?: return
        val historyData = mapOf(
            "id" to historyId,
            "branch" to selectedBranch,  // Store selected branch name
            "tableName" to tableName,
            "message" to message,
            "name" to name,  // Add name to the database
            "phone" to phone,  // Add phone to the database
            "timestamp" to System.currentTimeMillis()
        )

        databaseRef.child(historyId).setValue(historyData)
            .addOnCompleteListener {
                Toast.makeText(this, "Saved to database: $message", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { error ->
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
