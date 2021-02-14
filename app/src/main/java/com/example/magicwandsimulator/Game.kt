package com.example.magicwandsimulator

import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout


class Game(private var hpBar: CardView , private var manaBar:CardView) {

    private var shieldId = 0;
    var hp = 100.00;
    var mana = 0.00;
    private val maxMana = 90.00
    private val maxHp = 100.00
    private val width = MainActivity.getScreenWidth().toDouble()

    fun changeShield(id:Int){
        shieldId = id
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


    fun changeHp(change:Int){
        if (hp + change >= maxHp){
            hp = maxHp;
        }

        else if (hp + change <= 0){
            hp = 0.00;
        }
        else{
            hp += change
        }
        setHPBar()
    }

    fun setHPBar(){
        android.util.Log.e("tag" , ((hp/maxHp) * width).toString())
        var param = ConstraintLayout.LayoutParams((((hp/maxHp) * width)).toInt() , ConstraintLayout.LayoutParams.MATCH_PARENT)
        manaBar.layoutParams = param
        manaBar.radius = 30f
    }


}