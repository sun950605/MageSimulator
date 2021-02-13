package com.example.magicwandsimulator

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import com.plattysoft.leonids.ParticleSystem


class SpellFactory(private val activity: FragmentActivity) {



    fun meteor(){
        ParticleSystem(activity, 10, R.mipmap.star1, 10000)
            .setSpeedModuleAndAngleRange(0.05f, 0.2f, 30, 80)
            .emit(0, 0, 1, 10000)

        ParticleSystem(activity, 10, R.mipmap.star2, 10000)
            .setSpeedModuleAndAngleRange(0.05f, 0.4f, 30, 80)
            .emit(-100, 100, 1, 10000)

        ParticleSystem(activity, 10, R.mipmap.star2, 10000)
            .setSpeedModuleAndAngleRange(0.05f, 0.2f, 30, 80)
            .emit(-100, 100, 1, 10000)
    }



}