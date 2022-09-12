package com.example.firebasetext

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var Email: EditText? = null
    private var Password: EditText? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var prg:ProgressDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Email = findViewById(R.id.emailll)
        Password = findViewById(R.id.passworddd)
        firebaseAuth = FirebaseAuth.getInstance()
        prg= ProgressDialog(this)
        login.setOnClickListener {
            validate()
        }
        show.setOnClickListener {
            startActivity(Intent(this,Loaddata::class.java))
        }
        forget.setOnClickListener {
            startActivity(Intent(this,ForgetP::class.java))
        }
        val user= firebaseAuth!!.currentUser
        if (user!=null){
            startActivity(Intent(this, SecoundryActivity::class.java))

        }
        signup.setOnClickListener {
            startActivity(Intent(this,SignUp::class.java))
        }
    }

    fun validate() {
        var email = Email?.text.toString()
        var password = Password?.text.toString()
        prg?.setMessage("Please wait...")
        prg?.show()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Fill all information", Toast.LENGTH_SHORT).show()
        } else {
            firebaseAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        prg?.dismiss()
                             verified()
                    }else{
                        prg?.dismiss()
                        Toast.makeText(this, "Errror something while Wrong!!", Toast.LENGTH_SHORT).show()
                    }



                }
        }
    }


    fun verified(){
        val firebaseUser=FirebaseAuth.getInstance().currentUser
        val vemail= firebaseUser!!.isEmailVerified
        startActivity(Intent(this,SecoundryActivity::class.java))
        if (vemail){
            finish()
            startActivity(Intent(this,SecoundryActivity::class.java))

            Toast.makeText(this, "Sucessfully login!!", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Please Verify your Email", Toast.LENGTH_SHORT).show()
            firebaseAuth?.signOut()

        }
    }
}
