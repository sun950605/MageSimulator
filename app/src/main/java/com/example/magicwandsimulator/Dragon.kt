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
    var type = 1
    fun idle(){
        beingHit = false
        drgView.apply {
            setBackgroundResource(R.drawable.drag_idle)
            anim = background as AnimationDrawable
        }
        anim.start()
    }


    fun hit(){
        android.util.Log.e("tag" , "hitting!!! ${changiing}")
        if(changiing != true) {
            beingHit = true
            //anim.stop()
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
            val timerTask: TimerTask = timerTask {
                idle()
                beingHit = false
            }
            timer.schedule(timerTask, totalDuration)
        }
    }

    fun attack(type:Int){
        //anim.stop()
        attacking = true
        var resourceID = if (type == 1){
            R.drawable.drag_fire
        }else if (type == 2){
            R.drawable.drag_water
        }
        else{
            R.drawable.drag_elec
        }

        drgEffectView.visibility= View.VISIBLE
        drgView.apply {
            setBackgroundResource(R.drawable.drag_attack2)
            anim = background as AnimationDrawable
        }

        drgEffectView.apply {
            setBackgroundResource(resourceID)
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
            game.damagePlayer(FIRE_DAMAGE , type)
        }
        timer.schedule(timerTask, totalDuration)
        attacking = false


    }

    fun setGame(_game:Game){
        game = _game
    }

    fun changeState(type:Int){

        anim.stop()
        changiing = true
        android.util.Log.e("tag" ,"changing = ${changiing.toString()}")
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
        val timerTask: TimerTask = timerTask{
            idle()
            changiing = false
            game.changeMap(type)
            android.util.Log.e("tag" ,"changing = ${changiing.toString()}")
        }
        timer.schedule(timerTask, totalDuration)

    }












}