package com.example.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.airbnb.lottie.LottieAnimationView


class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val animation=findViewById<LottieAnimationView>(R.id.animationView)
        animation.setAnimation("memelogo.json")
        animation.playAnimation()

        handler= Handler()
        handler.postDelayed({

            val intent= Intent(this,MemeActivity::class.java)
            startActivity(intent)
            finish()
        },3000)



    }


}