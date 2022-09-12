package com.example.firebasetext

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_retrave.*

class Retrave : AppCompatActivity() {
private lateinit var uri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrave)
      val firebaseStorage=FirebaseStorage.getInstance().getReference("Image")
        firebaseStorage.downloadUrl.addOnSuccessListener { uri->
            Picasso.get().load(uri).into(imageView)
        }



    }
}