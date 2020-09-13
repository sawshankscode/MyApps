package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.MainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View?) {
        val intent = Intent(this, TicTacToe::class.java)
        val editText1 = findViewById<EditText>(R.id.editText1)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val message1 = editText1.text.toString()
        val message2 = editText2.text.toString()
        if (message1.isEmpty() && message2.isEmpty()) {
            Toast.makeText(this@MainActivity, "Please enter Players name", Toast.LENGTH_LONG).show()
        } else if (message2.isEmpty()) {
            Toast.makeText(this@MainActivity, "Please enter Player 2 name", Toast.LENGTH_LONG).show()
        } else if (message1.isEmpty()) {
            Toast.makeText(this@MainActivity, "Please enter Player 1 name", Toast.LENGTH_LONG).show()
        } else {
            intent.putExtra(MSG1, message1)
            intent.putExtra(MSG2, message2)
            startActivity(intent)
        }
    }

    companion object {
        const val MSG1 = "com.example.tictactoe.name1"
        const val MSG2 = "com.example.tictactoe.name2"
    }
}