package com.example.magicwandsimulator

import android.R.attr.animation
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import java.util.*
import kotlin.concurrent.timerTask


class Dragon(private val drgView: ImageView){
    private lateinit var anim: AnimationDrawable

    private lateinit var idel2Anim: AnimationDrawable
    private  var attacking = false
    private  var beingHit = false
    fun idle(){
        beingHit = false
        drgView.apply {
            setBackgroundResource(R.drawable.drag_idle)
            anim = background as AnimationDrawable
        }
        anim.start()
    }


    fun hit(){
        if(!beingHit) {
            beingHit = true
            anim.stop()
            drgView.apply {
                setBackgroundResource(R.drawable.drag_attack1)
                anim = background as AnimationDrawable
            }

            anim.start()
            var totalDuration: Long = 0
            for (i in 0 until anim.numberOfFrames) {
                totalDuration += anim.getDuration(i)
            }
            val timer = Timer()
            val timerTask: TimerTask = timerTask { idle() }
            timer.schedule(timerTask, totalDuration)
        }
    }

    fun attack2(){
        anim.stop()
        attacking = true
        drgView.apply {
            setBackgroundResource(R.drawable.drag_attack2)
            anim = background as AnimationDrawable
        }

        anim.start()
        var totalDuration: Long = 0
        for (i in 0 until anim.numberOfFrames) {
            totalDuration += anim.getDuration(i)
        }
        val timer = Timer()
        val timerTask: TimerTask = timerTask{ idle()}
        timer.schedule(timerTask, totalDuration)
        attacking = false
    }












}