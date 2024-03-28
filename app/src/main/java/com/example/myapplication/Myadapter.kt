package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.myapplication.databinding.MaterialsScrollviewBinding
import java.text.FieldPosition

class Myadapter(private val userList:List<scrollviewdata>):RecyclerView.Adapter<Myadapter.Myviewholder> () {
//private lateinit var mListener: onItemClickListener
//
//    interface onItemClickListener{
//        fun onItemClick(position: Int)
//    }
//
//    fun setonitemclicklistener(listener: onItemClickListener){
//        mListener=listener
//    }

//    val itemView = LayoutInflater.from(parent.context)
//        .inflate(R.layout.materials_scrollview, parent, false)
//    return Myviewholder(itemView)


//    var onItemClick: ((scrollviewdata) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        val binding =
            MaterialsScrollviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Myviewholder(binding)
//        val view = LayoutInflater.from(parent.context)
//        .inflate(R.layout.materials_scrollview, parent, false)
//    return Myviewholder(view)
    }

//    fun onapplysearch(userList:List<scrollviewdata>){
//        this.userList=userList
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        val currentitem = userList[position]
        holder.bind(currentitem)
//
//        holder.rentper.text=currentitem.rentper
//        holder.addressa.text=currentitem.addressa

//        holder.itemView.setOnClickListener {
//            onItemClick?.invoke(currentitem)
//        }
        val cont=holder.constraint.context
holder.constraint.setOnClickListener{
    val intent=Intent(it.context,description::class.java)
    intent.putExtra("rentper",currentitem.rentper)
    intent.putExtra("addressa",currentitem.addressa)
    intent.putExtra("contact",currentitem.contact)
    intent.putExtra("bedroom",currentitem.bedroom)
    intent.putExtra("bathroom",currentitem.bathroom)
    intent.putExtra("dyning",currentitem.dyning)
    intent.putExtra("drawing",currentitem.drawing)
    intent.putExtra("rentad",currentitem.rentad)
    intent.putExtra("descriptiona",currentitem.descriptiona)
    intent.putExtra("emailowner",currentitem.emailowner)

    it.context.startActivity(intent)

    Toast.makeText(cont,"the item${currentitem.rentper} is clicked",Toast.LENGTH_SHORT).show()
}

    }
//   class Myviewholder(itemView: View):RecyclerView.ViewHolder(itemView) {


       class Myviewholder(private val binding: MaterialsScrollviewBinding) :
           RecyclerView.ViewHolder(binding.root) {
           fun bind(currentitem: scrollviewdata) {
               binding.rent.text = currentitem.rentper

               binding.location.text = currentitem.addressa


//        val rentper:TextView=itemView.findViewById(R.id.rent)
//       val addressa:TextView=itemView.findViewById(R.id.address)


           }
           val constraint:ConstraintLayout=itemView.findViewById(R.id.newlayout)


       }
   }


