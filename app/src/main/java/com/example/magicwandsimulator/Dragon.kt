package com.example.magicwandsimulator

import android.R.attr.animation
import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
import java.util.*
import kotlin.concurrent.timerTask


class Dragon(private val drgView: ImageView , private val drgEffectView:ImageView){
    private lateinit var anim: AnimationDrawable
    private lateinit var efffectAnim: AnimationDrawable
    private lateinit var idel2Anim: AnimationDrawable
    private lateinit var game:Game
    private  var attacking = false
    private  var beingHit = false
    private  var changiing = false
    private var FIRE_DAMAGE = 20.00
    private var hp = 100
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

    fun attack(){
        //anim.stop()
        attacking = true
        drgEffectView.visibility= View.VISIBLE
        drgView.apply {
            setBackgroundResource(R.drawable.drag_attack2)
            anim = background as AnimationDrawable
        }

        drgEffectView.apply {
            setBackgroundResource(R.drawable.drag_fire)
            efffectAnim = background as AnimationDrawable
        }

        anim.start()
        efffectAnim.start()

        var totalDuration: Long = 0
        for (i in 0 until anim.numberOfFrames) {
            totalDuration += anim.getDuration(i)
        }
        val timer = Timer()
        val timerTask: TimerTask = timerTask{
            idle()
            drgEffectView.visibility= View.INVISIBLE
            game.damagePlayer(FIRE_DAMAGE , 1)
        }
        timer.schedule(timerTask, totalDuration)
        attacking = false


    }

    fun setGame(_game:Game){
        game = _game
    }

    fun changeState(){
        anim.stop()
        changiing = true
        drgView.apply {
            setBackgroundResource(R.drawable.drag_change_state)
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
        changiing = false
    }












}