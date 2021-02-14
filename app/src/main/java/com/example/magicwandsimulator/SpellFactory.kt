package com.example.magicwandsimulator

import androidx.fragment.app.FragmentActivity
import com.plattysoft.leonids.ParticleSystem


class SpellFactory(private val activity: FragmentActivity) {

    fun meteor(){
        ParticleSystem(activity, 10, R.mipmap.star1, 20000)
            .setSpeedModuleAndAngleRange(0.5f, 1f, 30, 80)
            .emit(0, 0, 100, 10000)

        ParticleSystem(activity, 10, R.mipmap.star4, 10000)
            .setSpeedModuleAndAngleRange(0.4f, 1f, 10, 80)
            .emit(0, 0, 100, 10000)
    }


    fun lightning(){
        ParticleSystem(activity, 1000, R.mipmap.lighting1, 10000)
            .setSpeedModuleAndAngleRange(0.4f, 0.4f, 0, 360)
            .emit(500, 500, 300, 1000)
    }

    fun fire(){

        ParticleSystem(activity, 1000, R.mipmap.fa1, 10000)
            .setSpeedModuleAndAngleRange(0.2f, 0.5f, 0, 360)
            .emit(500, 500, 200, 1000)

        ParticleSystem(activity, 1000, R.mipmap.fa1, 10000)
            .setSpeedModuleAndAngleRange(0.2f, 0.5f, 0, 360)
            .emit(500, 500, 200, 1000)
    }


    fun water(){

        ParticleSystem(activity, 1000, R.mipmap.water1, 10000)
            .setSpeedModuleAndAngleRange(0.2f, 0.5f, 0, 360)
            .emit(500, 500, 200, 1000)

        ParticleSystem(activity, 1000, R.mipmap.water2, 10000)
            .setSpeedModuleAndAngleRange(0.2f, 0.5f, 0, 360)
            .emit(500, 500, 20, 1000)
    }





}