package com.ilaydabykakova.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ilaydabykakova.timer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number = 0
    // When the stop button is pressed without taking action, it helps that app to shutdown
    var runnable : Runnable = Runnable{ }
    var handler : Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    fun start(view: View){
        /* //Wrong Way
        while (number < 100) {
            number = number + 1
            binding.textView.text = "Time :  ${number}"
            // This calculates in seconds and gives the output directly to 100.
            //wa
            Thread.sleep(1000)
        }
         */
        runnable = object : Runnable{
            override fun run() {
                number++;
                binding.textView.text = "Timer : ${number}"
                //this -> reference to runnable
                handler.postDelayed(this,1000)

            }
        }
        //runnable can start here
        handler.post(runnable)
        binding.button.setEnabled(false)

    }
    fun  resume(view: View){

        handler.removeCallbacks(runnable)
        binding.button.setEnabled(true)

    }
    fun  stop(view: View){
        binding.button.setEnabled(true)
        handler.removeCallbacks(runnable)
        number = 0
        binding.textView.text = "Timer : 0"
    }
}