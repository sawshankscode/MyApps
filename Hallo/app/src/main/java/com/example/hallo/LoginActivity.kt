package com.example.hallo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_login.login_btn as login_btn

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val toolbar: Toolbar =findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)
        // supportActionBar!!.title=""
        supportActionBar!!.title="Login"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            val intent= Intent(this@LoginActivity,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        mAuth=FirebaseAuth.getInstance()
        login_btn.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email:String=email_login.text.toString()
        val password:String=password_login.text.toString()


         if(email=="")
            Toast.makeText(this@LoginActivity,"Please enter Email", Toast.LENGTH_LONG).show()
        else if(password=="")
            Toast.makeText(this@LoginActivity,"Please enter Password", Toast.LENGTH_LONG).show()
        else{
             mAuth.signInWithEmailAndPassword(email,password)
                 .addOnCompleteListener { task->
                     if(task.isSuccessful){
                         val intent= Intent(this@LoginActivity,MainActivity::class.java)
                         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) //this step is done so that user doesn't gets logged out
                         startActivity(intent)                                                                   //just by pressing back button
                         finish()
                     }
                     else
                         Toast.makeText(this@LoginActivity,"Error Message:"+task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                 }
         }

    }
}