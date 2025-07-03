package com.latnok.expense_tracker_kotlin.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.latnok.expense_tracker_kotlin.R
import com.latnok.expense_tracker_kotlin.ui.home.HomeActivity
import com.latnok.expense_tracker_kotlin.ui.welcome.WelcomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<ImageButton>(R.id.backButton).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewModel.state.observe(this) { state ->
            when (state) {
                is RegisterState.Loading -> {
                    findViewById<Button>(R.id.registerButton).isEnabled = false
                }
                is RegisterState.Success -> {
                    Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()
                }
                is RegisterState.Error -> {
                    findViewById<Button>(R.id.registerButton).isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            val name = findViewById<EditText>(R.id.nameInput).text.toString()
            val email = findViewById<EditText>(R.id.emailInput).text.toString()
            val password = findViewById<EditText>(R.id.passwordInput).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.confirmPasswordInput).text.toString()

            viewModel.register(name, email, password, confirmPassword)
        }
    }
}
