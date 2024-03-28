package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityAddpostingBinding
import com.example.myapplication.databinding.ActivityLoginactivityBinding
import com.example.myapplication.screens.homescreen
import com.example.myapplication.screens.signupactivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.Base64

class addposting : AppCompatActivity() {
    var simage:String?=""
    private lateinit var binding:ActivityAddpostingBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth:FirebaseAuth
    private lateinit var imageView: ImageView
    private lateinit var buttongallery:Button
    private lateinit var buttonpost:Button
    private lateinit var storage: FirebaseStorage
    private lateinit var uri:Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAddpostingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference=FirebaseDatabase.getInstance().reference
        auth= FirebaseAuth.getInstance()

        storage= FirebaseStorage.getInstance()


//        binding.button15.setOnClickListener{
//            startActivity(Intent(this@addposting,homescreen::class.java))
//
//        }

        imageView=findViewById(R.id.imageView10)
        buttongallery=findViewById(R.id.galleryimage)
        buttonpost=findViewById(R.id.uploadimage)
        val galleryimage=registerForActivityResult(ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView.setImageURI(it)

                    uri= it!!

            })
        buttongallery.setOnClickListener{
            galleryimage.launch("image/*")
        }
        buttonpost.setOnClickListener{
            storage.getReference("Images").child(System.currentTimeMillis().toString()).putFile(uri).addOnSuccessListener { task->
                task.metadata!!.reference!!.downloadUrl.addOnSuccessListener {urll->
                    Toast.makeText(this, "image downloaded", Toast.LENGTH_SHORT).show()
//                    val imageurl=url.toString()
                    val uid=FirebaseAuth.getInstance().currentUser!!.uid
                    val imagemap= mapOf(
                        "url" to urll.toString()
                    )
                    val databaseReference=FirebaseDatabase.getInstance().getReference("userimages")
                    databaseReference.child(uid).setValue(imagemap).addOnSuccessListener {
                        Toast.makeText(this, "image upload successful", Toast.LENGTH_SHORT).show()
                    }
                        .addOnFailureListener {
                            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }


//
//        binding.button4.setOnClickListener{
//            startActivity(Intent(this@addposting,propertypictures::class.java))
//
//        }

        binding.button19.setOnClickListener{
            val bedroom=binding.bedroom.text.toString()
            val bathroom=binding.bathroom.text.toString()
            val dyning=binding.dyning.text.toString()
            val drawing=binding.drawing.text.toString()
            val rentper=binding.rentper.text.toString()
            val rentad=binding.rentad.text.toString()
            val emailowner=binding.emailowner.text.toString()
            val contact=binding.contact.text.toString()
            val addressa=binding.address.text.toString()
            val descriptiona=binding.description.text.toString()


            val currentuser=auth.currentUser
            currentuser?.let { user->

                val newitem=NewItem(bedroom,bathroom,dyning,drawing,rentper, rentad,emailowner,contact,addressa,descriptiona)

                databaseReference.child("users").child(user.uid).setValue(newitem).addOnCompleteListener{task->
                    if(task.isSuccessful){
                        Toast.makeText( this@addposting,"data saved successfully!!", Toast.LENGTH_SHORT).show()
                        finish()

                    }
                    else{
                        Toast.makeText( this@addposting,"failed to save note", Toast.LENGTH_SHORT).show()

                    }

                }
            }

        }


    }


//    fun insert_image(view: View) {}
//    private val ActivityResultLauncher=registerForActivityResult<Intent,ActivityResult>(
//        ActivityResultContracts.StartActivityForResult()
//
//    ){ result:ActivityResult->
//        if(result.resultCode== RESULT_OK){
//            val uri =result.data!!.data
//            try {
//                val inputStream=contentResolver.openInputStream(uri!!)
//                val mybitmap=BitmapFactory.decodeStream(inputStream)
//                val stream=ByteArrayOutputStream()
//                mybitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
//                val bytes=stream.toByteArray()
//
//            }
//            catch (ex:Exception){
//                Toast.makeText(this,ex.message.toString(),Toast.LENGTH_LONG).show()
//            }
//        }
//    }




}