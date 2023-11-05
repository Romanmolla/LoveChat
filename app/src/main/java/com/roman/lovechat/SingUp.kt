package com.roman.lovechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.roman.lovechat.databinding.ActivitySingUpBinding

class SingUp : AppCompatActivity() {

    private val binding: ActivitySingUpBinding by lazy {
        ActivitySingUpBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.signBtn.setOnClickListener {
            startActivity(Intent(this,login::class.java))
            finish()
        }

        binding.registerBtn.setOnClickListener {

            //get text form edit text field
            val email1 = binding.email1.text.toString()
            val username = binding.username1.text.toString()
            val password = binding.password.text.toString()
            val repassword = binding.rePassword.text.toString()



            //check if any field is blank
            if(email1.isEmpty()|| username.isEmpty() || password.isEmpty() || repassword.isEmpty()){

                Toast.makeText(this, "plese fail all the details", Toast.LENGTH_SHORT).show()

            }else if(password != repassword){
                Toast.makeText(this, "repet password must be same", Toast.LENGTH_SHORT).show()

            } else{
                auth.createUserWithEmailAndPassword(email1, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,login::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this, "Registaraton Faild : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }

                    }
            }
        }

    }
}