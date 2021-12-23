package com.example.lab_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*


class MainActivity : AppCompatActivity() {
    private lateinit var rg : RadioGroup
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button_go)
        rg = findViewById(R.id.radioGroup)

        button.setOnClickListener {
            if (rg.checkedRadioButtonId == -1) {
                Toast.makeText(getApplicationContext(), "Please, select action.", Toast.LENGTH_LONG).show();
            } else {
                if(findViewById<RadioButton>(rg.checkedRadioButtonId).text as String == "Watch video") {
                    this.startActivity(Intent(this, ActivityVideo::class.java))
                }
                else {
                    this.startActivity(Intent(this, ActivityAudio::class.java))
                }
            }
        }
    }
}