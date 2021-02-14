package com.example.magicwandsimulator

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.andrognito.patternlockview.PatternLockView
import kotlin.random.Random


class Game(private val context: Context, private var hpBar: CardView, private var manaBar:CardView, private var shieldView: ImageView, private val dragon: Dragon, private val spellBook:PatternLockView, private val mapView:ConstraintLayout) {

    private var shieldId = 0;
    var hp = 100.00;
    var mana = 0.00;
    private val maxMana = 90.00
    private val maxHp = 100.00
    private val width = MainActivity.getScreenWidth().toDouble()
    var previousMap:Int = 0
    lateinit var shieldAnim: AnimationDrawable
    private  var timer: Long = 0
    private var gameCleared = false
    private var gameLoss = false
    private var gameWin = false
    fun init(){
        dragon.setGame(this)
        dragon.type = Random.nextInt(1,4)
        previousMap = dragon.type
        changeMap(dragon.type)
        startAttack()
        timer = SystemClock.elapsedRealtime();
    }

    fun changeShield(id:Int){
        shieldId = id
        applyShield(id)
    }


    fun setManaBar(){
        android.util.Log.e("tag" , ((mana/maxMana) * width).toString())
        var param = ConstraintLayout.LayoutParams((((mana/maxMana) * width)).toInt() , ConstraintLayout.LayoutParams.MATCH_PARENT)
        manaBar.layoutParams = param
        manaBar.radius = 30f
    }


    fun damagePlayer(damage:Double , damageType:Int){
        if (damageType != shieldId) {
            changeHp(damage)
        }else{
            removeShield()
            changeHp(-damage/2)
        }
    }

    fun applyShield(_shieldId:Int){
        shieldView.visibility = View.VISIBLE
        var resourceID = if(_shieldId == 1) {
            R.drawable.fire_shield
        }else if (_shieldId == 2){
            R.drawable.water_shield
        }else{
            R.drawable.elec_shield
        }

        shieldView.apply {
            setBackgroundResource(resourceID)
            shieldAnim = background as AnimationDrawable
        }
        shieldAnim.start()
    }

    fun removeShield(){
        shieldId = 0
        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = Runnable {
            shieldView.visibility = View.INVISIBLE
            changeMana(30)
        }
        handler.post(runnable)
    }

    fun changeMap(type:Int){
        val resourceID = if (type == 1){
            R.drawable.volcano
        }

        else if (type == 2){
            R.drawable.ice
        }
        else{
            R.drawable.space
        }
        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = Runnable {
            mapView.setBackgroundResource(resourceID)
            previousMap = type
        }
        handler.post(runnable)
    }


    fun changeMana(change:Int){
        if (mana + change >= maxMana){
            mana = maxMana;
        }

        else if (mana + change <= 0){
            mana = 0.00;
        }
        else{
            mana += change
        }
        setManaBar()
    }

    fun clearGame(){
        if (::shieldAnim.isInitialized){
            shieldAnim?.stop()
        }
        dragon.killDragon()
        if(!gameCleared){
            gameCleared = true
            val endTime = SystemClock.elapsedRealtime()
            val elapsedMilliSeconds: Long = endTime - timer
            val elapsedSeconds = elapsedMilliSeconds / 1000.0
            android.util.Log.e("game time" , elapsedSeconds.toString())
            val intent = Intent(context, EndGameActivity::class.java)
            intent.putExtra("TIME" , elapsedSeconds)
            if (gameLoss) {
                intent.putExtra("STAT", false)
            }else{
                intent.putExtra("STAT", true)
            }
            context.startActivity(intent)
        }

    }

    fun changeHp(change:Double){
        if (hp  - change <= 0.00){
            hp = 0.00
            gameLoss = true
            clearGame()
        }else if (hp - change  >= 100){
            hp = 100.00
        }else{
            hp -= change
        }

        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = Runnable {
            setHPBar()
        }
        handler.post(runnable)
    }

    fun startAttack(){
        var timer = Random.nextLong(3,6)
        var type = Random.nextInt(1, 4)
        var changeType = Random.nextInt(1,6)


        android.util.Log.e("tag", type.toString())
        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = Runnable {
            if (changeType == 1) {
                var mapType = Random.nextInt(1,4)
                while (mapType == previousMap){
                    mapType = Random.nextInt(1,4)
                }
                dragon.changeState(mapType)
                previousMap = mapType
                if(!gameLoss) {
                    startAttack()
                }
            }else{
                dragon.attack(type)
                if(!gameLoss) {
                    startAttack()
                }
            }
        }
        handler.postDelayed(runnable , timer * 1000)
    }

    fun setHPBar(){
        android.util.Log.e("tag" , ((hp/maxHp) * width).toString())
        var param = ConstraintLayout.LayoutParams((((hp/maxHp) * width)).toInt() , ConstraintLayout.LayoutParams.MATCH_PARENT)
        hpBar.layoutParams = param
        hpBar.radius = 30f
    }


}