package com.roman.lovechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.roman.lovechat.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth

//    override fun finish() {
//        super.finish()
//        //ckack if user already logged in
//        val currentUser : FirebaseUser? = auth.currentUser
//        if (currentUser != null){
//            startActivity(Intent(this,MainActivity::class.java))
//            finish()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()



        binding.loginBtn.setOnClickListener {
            val userName = binding.username.text.toString()
            val password = binding.password1.text.toString()

            if (userName.isEmpty()|| password.isEmpty()){
                Toast.makeText(this, "please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(userName, password)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Signing successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "sign-in failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.signupBtn.setOnClickListener {
            startActivity(Intent(this, SingUp::class.java))
            finish()
        }
    }
}