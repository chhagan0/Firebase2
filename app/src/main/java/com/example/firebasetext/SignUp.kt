package com.example.firebasetext

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.firebasetext.Realtime.User
import com.example.firebasetext.Realtime.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.signup
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    private lateinit var DR: DatabaseReference
    private var Email: EditText? = null
    private var Password: EditText? = null

    private var prg: ProgressDialog? = null
lateinit var uri:Uri
    private lateinit var Upasswordd: EditText
    private lateinit var Unamee: EditText

    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        Email = findViewById(R.id.emailll)
        Password = findViewById(R.id.passworddd)
        Unamee = findViewById(R.id.nameeee)

        Upasswordd = findViewById(R.id.consformppp)
        firebaseAuth = FirebaseAuth.getInstance()
        DR = FirebaseDatabase.getInstance().getReference("Users")
        prg = ProgressDialog(this)
        signup.setOnClickListener {
            newData()
            sendimage()
        }
        foto.setOnClickListener {
            imagesent()
        }
    }

    private fun sendimage() {
val firebaseStorage=FirebaseStorage.getInstance().getReference("Image")
        firebaseStorage.putFile(uri).addOnSuccessListener {
            Toast.makeText(
                this,
                "Sucessful....",
                Toast.LENGTH_SHORT
            ).show() }
    }

    private fun imagesent() {
        val intent=Intent()
        intent.type="image/*"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,12)

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if  (requestCode==12 && resultCode==Activity.RESULT_OK && data !=null&& data.data !=null)
//            (requestCode==Image_REQUESt && resultCode==Activity.RESULT_OK && data !=null&& data.data !=null
        {
            uri= data.data!!
            Picasso.get().load(uri).into(foto)
        }
    }

    fun newData() {


        val e = Email?.text.toString()
        val p = Password?.text.toString()
        prg?.setMessage("Please wait...")
        prg!!.show()
        if (e.isEmpty() || p.isEmpty()) {
            prg!!.dismiss()
            Toast.makeText(this, "Please fill all detail", Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuth!!.createUserWithEmailAndPassword(e, p).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    sendData()
                    sendemail()

                    prg!!.dismiss()


                } else {
                    prg!!.dismiss()
                    Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                }


            }

        }
    }

    fun sendemail() {

        firebaseAuth?.currentUser!!.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Sucessful!!", Toast.LENGTH_SHORT).show()
                firebaseAuth!!.signOut()
                finish()

                startActivity(Intent(this, MainActivity::class.java))

            } else {
                Toast.makeText(this, "Error ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendData() {
        val UserName = Unamee.text.toString()
        val UserPassword = Upasswordd.text.toString()
        val UserEmail= Email?.text.toString()

        val UserAge=ageeee.text.toString()
        val user=User(UserPassword,UserName,UserEmail,UserAge)
        DR.child(UserName).setValue(user)

    }

}