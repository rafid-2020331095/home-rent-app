package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.arch.core.executor.DefaultTaskExecutor
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityLoginactivityBinding
import com.example.myapplication.databinding.ActivityScrollhomeBinding
import com.example.myapplication.screens.homescreen
import com.example.myapplication.screens.signupactivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale

class scrollhome : AppCompatActivity() {
    private lateinit var binding:ActivityScrollhomeBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<scrollviewdata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityScrollhomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.button14.setOnClickListener{
//            startActivity(Intent(this@scrollhome,homescreen::class.java))
//
//        }


        databaseReference=FirebaseDatabase.getInstance().reference
        auth=FirebaseAuth.getInstance()

//        userRecyclerView=binding.newscrollview
//        userRecyclerView.layoutManager=LinearLayoutManager(this)

        userRecyclerView=findViewById(R.id.newscrollview)
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

       userArrayList= arrayListOf<scrollviewdata>()



//        val adapter=Myadapter(userArrayList)
//        searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//
//                val searchlist=ArrayList<scrollviewdata>()
//                if(newText!=null){
//                    for(i in userArrayList){
//                        if(i.addressa!!.lowercase(Locale.ROOT).contains(newText)){
//                            searchlist.add(i)
//                        }
//                    }
//
//                    if(searchlist.isEmpty()){
//                        Toast.makeText(this@scrollhome,"no data found",Toast.LENGTH_SHORT).show()
//                    }
//                    else{
//                        adapter.onapplysearch(searchlist)
//                    }
//                }
//
//                return true
//            }
//
//
//        })

       getuserdata()
    }



   private fun getuserdata() {
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(scrollviewdata::class.java)
                        userArrayList.add(user!!)
                    }

                        val adapter = Myadapter(userArrayList)
                        userRecyclerView.adapter = adapter
//                    adapter.onItemClick={
//                       val intent= Intent(this@scrollhome,description::class.java)
//                        intent.putExtra("currentitem",it)
//                        startActivity(intent)
//                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }


}