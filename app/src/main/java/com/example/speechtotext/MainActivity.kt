package com.example.speechtotext

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var imageClick : ImageView
    lateinit var tapToTalk : TextView
    lateinit var listening : TextView
    lateinit var userSpeech : TextView
    private val RQ_SPEECH_REC = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageClick = findViewById(R.id.microphone)
        tapToTalk = findViewById(R.id.tapToTalk)
        listening = findViewById(R.id.listening)
        userSpeech = findViewById(R.id.speech)

        imageClick.setOnClickListener{

            if(tapToTalk.visibility == View.GONE){
                tapToTalk.visibility = View.VISIBLE
                listening.visibility = View.GONE
            }

            else{
                tapToTalk.visibility = View.GONE
                listening.visibility = View.VISIBLE
                startListening()
            }
        }
    }

    private fun startListening(){
            val speech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            speech.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            speech.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say Something")
            try{
                startActivityForResult(speech, RQ_SPEECH_REC)
            }
            catch (e : Exception){
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val finalSpeech = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
            userSpeech.text = finalSpeech.toString()
            tapToTalk.visibility = View.VISIBLE
            listening.visibility = View.GONE
        }

    }

}
