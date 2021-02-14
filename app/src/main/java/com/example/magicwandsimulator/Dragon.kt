package com.example.magicwandsimulator

import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView

class Dragon(private val drgView: ImageView){
    private lateinit var idelAnim: AnimationDrawable

    private lateinit var idel2Anim: AnimationDrawable

    fun idle(){
        drgView.apply {
            setBackgroundResource(R.drawable.drag_idle)
            idelAnim = background as AnimationDrawable
        }
        idelAnim.start()
    }


    fun idle2(){
        drgView.apply {
            setBackgroundResource(R.drawable.drag_idle)
            idelAnim = background as AnimationDrawable
        }
        idelAnim.start()
    }












}