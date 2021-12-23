package com.example.lab_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView

class ActivityVideo : AppCompatActivity() {
    private lateinit var button_search : Button
    private lateinit var player : VideoView
    private lateinit var mediaController : MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        player = findViewById(R.id.player)
        mediaController = MediaController(this)
        button_search = findViewById(R.id.button_search)
        button_search.setOnClickListener {
            mediaController.hide()
            player.stopPlayback()
            val videoChooserIntent = Intent(Intent.ACTION_GET_CONTENT)
            videoChooserIntent.setType("video/*")
            startActivityForResult(Intent.createChooser(videoChooserIntent, "select video"), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            val uploadfileuri = data.data
            player.setVideoURI(uploadfileuri)
            player.setMediaController(mediaController)
            mediaController.setMediaPlayer(player)
        }
    }
}