package com.example.lab_4

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ActivityAudio : AppCompatActivity() {
    private lateinit var button_start : Button
    private lateinit var button_pause : Button
    private lateinit var button_stop : Button
    private var mp: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
        button_start = findViewById(R.id.start)
        button_pause = findViewById(R.id.pause)
        button_stop = findViewById(R.id.stop)
    }

    fun start(view: View) {
        if (mp == null) {
            val audioChooserIntent = Intent(Intent.ACTION_GET_CONTENT)
            audioChooserIntent.setType("audio/*")
            startActivityForResult(Intent.createChooser(audioChooserIntent, "select audio"), 1)
        } else {
            mp?.start()
        }
    }

    fun pause(view: View) {
        if (mp != null) {
            mp?.pause()
        }
    }

    fun stop(view: View) {
        if (mp != null) {
            mp?.stop()
            mp?.release()
            mp = null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val uploadfileuri = data.data
            mp = MediaPlayer.create(this, uploadfileuri)
            mp?.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mp != null) {
            mp?.stop()
            mp?.release()
            mp = null
        }
    }
}