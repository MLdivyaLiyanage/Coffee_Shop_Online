package com.example.coffee_shop_online

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FeedbackActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private var selectedRating: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)


        database = FirebaseDatabase.getInstance().reference

        val feedbackInput: EditText = findViewById(R.id.feedback_input)
        val sendButton: Button = findViewById(R.id.send_feedback)
        val star1: ImageView = findViewById(R.id.star_1)
        val star2: ImageView = findViewById(R.id.star_2)
        val star3: ImageView = findViewById(R.id.star_3)
        val star4: ImageView = findViewById(R.id.star_4)
        val star5: ImageView = findViewById(R.id.star_5)

        val stars = listOf(star1, star2, star3, star4, star5)

        // Star rating selection
        for (i in stars.indices) {
            stars[i].setOnClickListener {
                selectedRating = i + 1
                updateStarImages(stars, selectedRating)
            }
        }

        sendButton.setOnClickListener {
            val feedback = feedbackInput.text.toString().trim()

            if (selectedRating == 0) {
                Toast.makeText(this, "Please select a rating", Toast.LENGTH_SHORT).show()
            } else if (feedback.isEmpty()) {
                Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show()
            } else {
                saveFeedback(selectedRating, feedback)
            }
        }
    }

    private fun updateStarImages(stars: List<ImageView>, rating: Int) {
        for (i in stars.indices) {
            stars[i].setImageResource(if (i < rating) R.drawable.star else R.drawable.starout)
        }
    }

    private fun saveFeedback(rating: Int, feedback: String) {
        val feedbackId = database.child("feedback").push().key ?: return

        val feedbackData = Feedback(rating, feedback)

        // Save feedback data to the Firebase database
        database.child("feedback").child(feedbackId).setValue(feedbackData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Feedback submitted successfully!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, CategoryActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to submit feedback. Try again!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
