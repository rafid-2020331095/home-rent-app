package com.example.myapplication.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.NewItem
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySignupactivityBinding
import com.example.myapplication.userdata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class signupactivity : AppCompatActivity() {
    private lateinit var  binding:ActivitySignupactivityBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignupactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        firebaseDatabase=FirebaseDatabase.getInstance()
        databaseReference=firebaseDatabase.reference.child("users")

        binding.signupbutton.setOnClickListener{
            val signupusername=binding.signupusername.text.toString()
            val signuppassword=binding.signupPassword.text.toString()
            val email=binding.email.text.toString()

            if(signupusername.isNotEmpty()&& signuppassword.isNotEmpty()&&email.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email,signuppassword).addOnCompleteListener(this){ task->
                    if(task.isSuccessful){
                        Toast.makeText( this,"authentication complete", Toast.LENGTH_SHORT).show()
//                        signupuser(signupusername,signuppassword)
                        startActivity(Intent(this@signupactivity,loginactivity::class.java))

                    }
                    else{
                        Toast.makeText( this,"authentication failed:${task.exception?.message}", Toast.LENGTH_SHORT).show()

                    }

                }

            }
            else{
                Toast.makeText( this@signupactivity,"All fields are mandatory!!", Toast.LENGTH_SHORT).show()

            }
        }
        binding.loginredirect.setOnClickListener{
            startActivity(Intent(this@signupactivity,loginactivity::class.java))
            finish()
        }
    }
//
//    private fun signupuser(username: String,password:String){
//
//        val currentuser=auth.currentUser
//        currentuser?.let { user->
//
//            val userdata=userdata(username,password)
//
//            databaseReference.child(user.uid).setValue(userdata)
//
//        }

    }

