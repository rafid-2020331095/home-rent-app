package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityDescriptionBinding
import com.example.myapplication.databinding.ActivityLoginactivityBinding
import com.example.myapplication.screens.signupactivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class description : AppCompatActivity() {
    private lateinit var binding:ActivityDescriptionBinding
    private lateinit var imageView: ImageView
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.button16.setOnClickListener{
//            startActivity(Intent(this@description,scrollhome::class.java))
//
//        }

        val userid=FirebaseAuth.getInstance().currentUser!!.uid
        imageView=findViewById(R.id.imageView6)
        databaseReference=FirebaseDatabase.getInstance().getReference("userimages")
        databaseReference.child(userid).get().addOnSuccessListener {
            val url=it.child("url").value.toString()
            Glide.with(this).load(url).into(imageView)

        }

        val intent=intent

        val rent=intent?.getStringExtra("rentper")
        val address=intent?.getStringExtra("addressa")
        val contacte=intent?.getStringExtra("contact")
        val bedroom=intent?.getStringExtra("bedroom")
        val bathroom=intent?.getStringExtra("bathroom")
        val dyning=intent?.getStringExtra("dyning")
        val drawing=intent?.getStringExtra("drawing")
        val rentad=intent?.getStringExtra("rentad")
        val descriptiona=intent?.getStringExtra("descriptiona")
        val emailowner=intent?.getStringExtra("emailowner")

        binding.button5.setText(bedroom)
        binding.button17.setText(bathroom)
        binding.button8.setText(dyning)
        binding.button9.setText(drawing)
        binding.button7.setText(rent)
        binding.button11.setText(rentad)
        binding.textView22.setText(emailowner)
        binding.textView14.setText(address)
        binding.button6.setText(contacte)
        binding.textView15.setText(descriptiona)


    }
}