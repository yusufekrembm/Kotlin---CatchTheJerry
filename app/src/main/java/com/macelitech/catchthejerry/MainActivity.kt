package com.macelitech.catchthejerry

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.media.Image
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1


class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler =Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        object : CountDownTimer(20000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeText.setText("Time Left : " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                timeText.setText("Time Finished!")
                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Do you want to restart Game ?")
                   builder .setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            val intent = intent
                            finish()
                            startActivity(intent)
                            // START THE GAME!
                        })
                    builder.setNegativeButton("No",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                            finish()
                        })
                // Show Alert
                builder.show()
            }
        }.start()

        //ImageArray
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()
    }

    fun countScore(view : View){
        score++
        scoreText.text = "Score : "+score
    }

    fun hideImages(){
        runnable = object : Runnable{ // sureklılık ve sure icin
            override fun run() {
                for(image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val random = Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }

}