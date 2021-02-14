package com.example.magicwandsimulator

import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout


class Game(private var hpBar: CardView , private var manaBar:CardView,private var shieldView: ImageView , private val dragon: Dragon ) {

    private var shieldId = 0;
    var hp = 100.00;
    var mana = 0.00;
    private val maxMana = 90.00
    private val maxHp = 100.00
    private val width = MainActivity.getScreenWidth().toDouble()
    private var gameOver = false;

    fun init(){
        dragon.setGame(this)
    }

    fun changeShield(id:Int){
        shieldId = id
        applyShield(id)
    }

    fun getShieldId():Int{
        return shieldId
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
        }
    }

    fun applyShield(_shieldId:Int){
        if(shieldId == 1) {
            shieldView.setBackgroundResource(R.color.fire)
        }else if (shieldId == 2){
            shieldView.setBackgroundResource(R.color.water)
        }else{
            shieldView.setBackgroundResource(R.color.elec)
        }
    }

    fun removeShield(){
        shieldId = 0
        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = Runnable {
            shieldView.setBackgroundResource(R.color.background)
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


    fun changeHp(change:Double){

        if (hp  - change <= 0.00){
            hp = 0.00
            gameOver = true
        }else{
            hp -= change
        }

        val handler = Handler(Looper.getMainLooper())
        val runnable:Runnable = Runnable {
            setHPBar()
        }
        handler.post(runnable)
    }

    fun setHPBar(){
        android.util.Log.e("tag" , ((hp/maxHp) * width).toString())
        var param = ConstraintLayout.LayoutParams((((hp/maxHp) * width)).toInt() , ConstraintLayout.LayoutParams.MATCH_PARENT)
        hpBar.layoutParams = param
        hpBar.radius = 30f
    }


}