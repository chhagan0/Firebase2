package com.example.firebasetext.Realtime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetext.R

class Myadapter(private val userList: ArrayList<UserProfile>):RecyclerView.Adapter<Myadapter.Myviewholde>() {







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholde {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return Myviewholde(itemView)
    }

    override fun onBindViewHolder(holder: Myviewholde, position: Int) {
val currentuser=userList[position]
        holder.tvage.text=currentuser.Uage
        holder.tvemail.text=currentuser.UEmail
        holder.tvname.text=currentuser.Uname
        holder.tvpassord.text=currentuser.Upassword


    }

    override fun getItemCount(): Int {
return  userList.size
    }
    class Myviewholde(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvname:TextView=itemView.findViewById(R.id.loadname)
        val tvemail:TextView=itemView.findViewById(R.id.loademaiil)
        val tvpassord:TextView=itemView.findViewById(R.id.loadpassword)
        val tvage:TextView=itemView.findViewById(R.id.loadsge)


    }
}