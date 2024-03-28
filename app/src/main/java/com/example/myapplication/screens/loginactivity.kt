package com.example.myapplication.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginactivityBinding
import com.example.myapplication.userdata
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class loginactivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginactivityBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.loginbutton.setOnClickListener{
            val loginUsername=binding.loginusername.text.toString()
            val loginPassword=binding.loginPasword.text.toString()
            if(loginUsername.isNotEmpty()&& loginPassword.isNotEmpty()){
                auth.signInWithEmailAndPassword(loginUsername,loginPassword).addOnCompleteListener{ task->
                    if(task.isSuccessful){
                        Toast.makeText( this@loginactivity,"LOGIN SUCCESSFUL,CONGRATS!!", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@loginactivity,homescreen::class.java))

                    }
                    else{
                        Toast.makeText( this@loginactivity,"OPPs LOGIN FAILED!!TRY AGAIN:${task.exception?.message}", Toast.LENGTH_SHORT).show()

                    }

                }
            }
            else{
                Toast.makeText( this@loginactivity,"All fields are mandatory!!", Toast.LENGTH_SHORT).show()

            }
        }
        binding.signupredirect.setOnClickListener{
            startActivity(Intent(this@loginactivity,signupactivity::class.java))

        }
    }


}