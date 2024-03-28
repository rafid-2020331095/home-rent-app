package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class testactivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testactivity)


        val userid= FirebaseAuth.getInstance().currentUser!!.uid
        imageView=findViewById(R.id.testimage)
        databaseReference= FirebaseDatabase.getInstance().getReference("userimages")
        databaseReference.child(userid).get().addOnSuccessListener {
            val url=it.child("url").value.toString()
            Glide.with(this).load(url).into(imageView)

        }
    }
}