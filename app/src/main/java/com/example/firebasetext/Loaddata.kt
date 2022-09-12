package com.example.firebasetext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasetext.Realtime.Myadapter
import com.example.firebasetext.Realtime.UserProfile
import com.google.firebase.database.*
import java.lang.Error

class Loaddata : AppCompatActivity() {
    private lateinit var dbref:DatabaseReference
    private lateinit var userArrayList: ArrayList<UserProfile>
    private lateinit var userRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loaddata)
        userRecyclerView=findViewById(R.id.userlist)
        userRecyclerView.layoutManager=LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf()
        getUserdata()
    }

    private fun getUserdata() {
        dbref=FirebaseDatabase.getInstance().getReference("Users")





        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (userSnapshott in snapshot.children){

                        var user = userSnapshott.getValue(UserProfile::class.java)
                        userArrayList.add(user!!)

                    }

                    userRecyclerView.adapter = Myadapter(userArrayList)


                }

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}