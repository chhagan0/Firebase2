package com.example.firebasetext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_secoundry.*

class SecoundryActivity : AppCompatActivity() {
    private var firebaseAuth:FirebaseAuth?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secoundry)
        firebaseAuth=FirebaseAuth.getInstance()
        button.setOnClickListener {
            firebaseAuth!!.signOut()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


}