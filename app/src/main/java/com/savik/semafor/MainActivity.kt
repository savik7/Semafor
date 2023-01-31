package com.savik.semafor

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
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
            is_run = true
        } else {
            timer?.cancel()
            view.setImageResource(R.drawable.button_start)
            imSemafor?.setImageResource(R.drawable.semafor_grey)
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
        }, 0, 1000)
    }
}