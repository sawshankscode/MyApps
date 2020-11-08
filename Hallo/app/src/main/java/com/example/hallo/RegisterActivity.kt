package com.example.hallo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    private lateinit var refUsers:DatabaseReference
    private var firebaseUserID:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val toolbar: Toolbar =findViewById(R.id.toolbar_register)
        setSupportActionBar(toolbar)
        // supportActionBar!!.title=""
        supportActionBar!!.title="SignUp"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            val intent= Intent(this@RegisterActivity,WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        mAuth=FirebaseAuth.getInstance()
        register_btn.setOnClickListener {
            registerUser()
        }
    }
    private fun registerUser(){
        val username:String=username_register.text.toString()
        val email:String=email_register.text.toString()
        val password:String=password_register.text.toString()
        if(username=="")
            Toast.makeText(this@RegisterActivity,"Please enter Username",Toast.LENGTH_LONG).show()
        else if(email=="")
            Toast.makeText(this@RegisterActivity,"Please enter Email",Toast.LENGTH_LONG).show()
        else if(password=="")
            Toast.makeText(this@RegisterActivity,"Please enter Password",Toast.LENGTH_LONG).show()
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                task->
                if(task.isSuccessful){
                    firebaseUserID=mAuth.currentUser!!.uid
                    refUsers=FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserID)
                    val userHashMap=HashMap<String,Any>()
                    userHashMap["uid"]=firebaseUserID
                    userHashMap["username"]=username
                    userHashMap["profile"]="https://firebasestorage.googleapis.com/v0/b/hallov1.appspot.com/o/profile.png?alt=media&token=2baad58b-70c2-46d4-9e76-0b7c131b93d4"
                    userHashMap["cover"]="https://firebasestorage.googleapis.com/v0/b/hallov1.appspot.com/o/cover.jpg?alt=media&token=f99a4419-0e60-4445-9faf-57a024e683e9"
                    userHashMap["status"]="offline"
                    userHashMap["search"]=username.toLowerCase()
                    userHashMap["facebook"]="https://m.facebook.com"
                    userHashMap["instagram"]="https://m.instagram.com"
                    userHashMap["website"]="https://m.google.com"
                    refUsers.updateChildren(userHashMap)
                        .addOnCompleteListener { task->
                        if(task.isSuccessful){
                            val intent= Intent(this@RegisterActivity,MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK) //this step is done so that user doesn't gets logged out
                            startActivity(intent)                                                                   //just by pressing back button
                            finish()
                        }

                    }

                }
                else{
                    Toast.makeText(this@RegisterActivity,"Error Message:"+task.exception!!.message.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}