package com.example.magicwandsimulator


import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.magicwandsimulator.R.*


class BackgroundSoundService : Service() {
    lateinit var player: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }
    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, raw.bgm)
        player.setLooping(true) // Set looping
        player.setVolume(1f, 1f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player.start()
        return Service.START_NOT_STICKY
    }

    override  fun onStart(intent: Intent?, startId: Int) {
        // TO DO
    }



    override fun onDestroy() {
        player.stop()
        player.release()
    }


    companion object {
        private val TAG: String? = null
    }
}