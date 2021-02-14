package com.example.magicwandsimulator

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class EndGameActivity : AppCompatActivity() {

    lateinit var restart: CardView
    lateinit var gameStat:TextView
    lateinit var time:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        restart= findViewById(R.id.game_restart_button)
        gameStat = findViewById(R.id.game_stat)
        time = findViewById(R.id.best_score)
    }

    override fun onStart() {
        super.onStart()

        restart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}