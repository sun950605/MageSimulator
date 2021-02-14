package com.example.magicwandsimulator

class Game {

    private var shieldId = 0;


    fun changeShield(id:Int){
        shieldId = id
    }

    fun getShieldId():Int{
        return shieldId
    }


}