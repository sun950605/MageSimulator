package com.example.magicwandsimulator

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class EndGameActivity : AppCompatActivity() {

    lateinit var restart: CardView
    lateinit var gameStat: TextView
    lateinit var time: TextView

    lateinit var end_drag: ImageView
    lateinit var end_anim: AnimationDrawable

    var win = false
    var time_elap = 1.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        restart = findViewById(R.id.game_restart_button)
        gameStat = findViewById(R.id.game_stat)
        time = findViewById(R.id.best_score)
        end_drag = findViewById(R.id.end_game_anim)

        intent.extras?.let {
            time_elap = it.getDouble("TIME")
            win = it.getBoolean("STAT")
        }

    }

    override fun onStart() {
        super.onStart()

        restart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        end_drag.apply {
            setBackgroundResource(R.drawable.drag_end)
            end_anim = background as AnimationDrawable
        }
        end_anim.start()

        if(!win){
            gameStat.text = "You Lost..."
        }

        time.text = "Time Taken: ${time_elap}"
    }

    override fun onDestroy() {
        super.onDestroy()
        end_anim.stop()
    }
}