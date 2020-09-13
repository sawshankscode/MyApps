package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TicTacToe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
        val status = findViewById<TextView>(R.id.status)
        status.text = intent.getStringExtra(MainActivity.MSG1) + "'s turn-Tap to Play"
    }

    var c = 0
    var gameActive = true

    //Player representation
    //0-X
    //1-O
    var activePlayer = 0
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    //State meanings
    //0-X
    //1-O
    //2-Null
    var winPositions = arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8), intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(0, 4, 8), intArrayOf(2, 4, 6))
    fun tapTap(view: View) {
        c++
        val img = view as ImageView
        val tappedImage = img.tag.toString().toInt()
        if (!gameActive) {
            gameReset(view)
        } else if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer
            img.translationY = -1000f
            if (activePlayer == 0 && c != 9) {
                img.setImageResource(R.drawable.x)
                img.setImageResource(R.drawable.x)
                activePlayer = 1
                val status = findViewById<TextView>(R.id.status)
                status.text = intent.getStringExtra(MainActivity.MSG2) + "'s turn-Tap to Play"
            } else if (activePlayer == 1 && c != 9) {
                img.setImageResource(R.drawable.o)
                activePlayer = 0
                val status = findViewById<TextView>(R.id.status)
                status.text = intent.getStringExtra(MainActivity.MSG1) + "'s turn-Tap to Play"
            } else {
                val status = findViewById<TextView>(R.id.status)
                status.text = "OOPS!!! Khichdi!!"
                gameReset(view)
            }
            img.animate().translationYBy(1000f).duration = 300
        }
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                gameActive = false
                var winnerStr: String
                winnerStr = if (gameState[winPosition[0]] == 0) {
                    intent.getStringExtra(MainActivity.MSG1) + " has Won!"
                } else {
                    intent.getStringExtra(MainActivity.MSG2) + " has Won!"
                }
                val status = findViewById<TextView>(R.id.status)
                status.text = winnerStr
            }
        }
    }

    fun gameReset(view: View?) {
        gameActive = true
        for (i in gameState.indices) gameState[i] = 2
        c = 0
        activePlayer = 0
        (findViewById<View>(R.id.imageView3) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView4) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView5) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView6) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView7) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView8) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView9) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView10) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView11) as ImageView).setImageResource(0)
    }
}