package com.example.firebasetext

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_p.*

class ForgetP : AppCompatActivity() {
    private var Email:EditText?=null
    private var firebaseAuth:FirebaseAuth?=null
    private var progressDialog:ProgressDialog?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_p)
        Email=findViewById(R.id.emailll)
        firebaseAuth=FirebaseAuth.getInstance()
        progressDialog= ProgressDialog(this)
        Reset.setOnClickListener {
            reset()
        }
    }
    fun reset(){
        progressDialog?.setMessage("Please wait")
        progressDialog!!.show()
        var email= Email?.text.toString()
        if (email.isEmpty()){
            progressDialog!!.dismiss()
            Toast.makeText(this, "Enter a valid Email", Toast.LENGTH_SHORT).show()
        }else
        {
            firebaseAuth?.sendPasswordResetEmail(email)?.addOnCompleteListener {  task->
                if (task.isSuccessful){
                    progressDialog!!.dismiss()
                    Toast.makeText(this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
                else{
                    progressDialog!!.dismiss()
                    Toast.makeText(this, "#error303", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}