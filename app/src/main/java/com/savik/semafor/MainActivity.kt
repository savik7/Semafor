package com.savik.semafor

import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import java.util.Timer
import java.util.TimerTask

class MainActivity : Activity() {
    var imSemafor:ImageView? = null
    var counter:Int = 0
    var timer:Timer? = null
    var is_run = false
    var imArray:IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_ell, R.drawable.semafor_green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imSemafor = findViewById(R.id.imSemafor)
    }

    fun onClickStartStop(view:View) {
        view as ImageButton
        if (!is_run){
            startStop()
            view.setImageResource(R.drawable.button_stop)
            val myToast = Toast.makeText(this, getString(R.string.on), Toast.LENGTH_SHORT) //Всплывающее сообщение по нажатию кнопки
            myToast.show() //Показываем всплывающее сообщение
            MediaPlayer.create(this, R.raw.click).start() // Звук кнопки при нажатии
            is_run = true
        } else {
            timer?.cancel()
            view.setImageResource(R.drawable.button_start)
            imSemafor?.setImageResource(R.drawable.semafor_grey)
            val myToast = Toast.makeText(this, getString(R.string.off), Toast.LENGTH_SHORT)
            myToast.show()
            MediaPlayer.create(this, R.raw.click).start()
            is_run = false
            counter = 0
        }
    }

    fun startStop(){
        timer = Timer()
        timer?.schedule(object: TimerTask(){
            override fun run() {
                runOnUiThread(){
                    imSemafor?.setImageResource(imArray[counter])
                    counter++
                    if (counter == 3){
                        counter = 0
                    }
                }
            }
        }, 0, 2000)
    }
}