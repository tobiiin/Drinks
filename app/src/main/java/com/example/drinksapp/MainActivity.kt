// MainActivity.kt
package com.example.drinksapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var challengeTextView: TextView
    private val allChallenges = mutableListOf<String>()
    private var challengesCompleted = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        challengeTextView = findViewById(R.id.challengeTextView)
        val newChallengeButton: Button = findViewById(R.id.newChallengeButton)

        // Initialize the list of challenges
        allChallenges.addAll(resources.getStringArray(R.array.challenges))

        newChallengeButton.setOnClickListener {
            if (challengesCompleted < 5) {
                generateChallenge()
            } else {
                navigateToGameOverScreen()
            }
        }
    }

    private fun generateChallenge() {
        if (allChallenges.isEmpty()) {
            challengeTextView.text = "No more challenges available!"
            return
        }

        // Remove the used challenge from the list
        val randomIndex = (0 until allChallenges.size).random()
        val randomChallenge = allChallenges.removeAt(randomIndex)

        challengeTextView.text = randomChallenge
        challengesCompleted++
    }

    private fun navigateToGameOverScreen() {
        val intent = Intent(this, GameOverActivity::class.java)
        startActivity(intent)
    }
}
