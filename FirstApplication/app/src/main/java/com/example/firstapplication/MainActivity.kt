package com.example.firstapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private val APP_PREFERENCES = "mysettings"
    private val APP_PREFENCES_EMAIL = "email"
    private val APP_PREFERENCES_IS_SIGN_IN = "isSignIn"
    private var email: String? = ""
    private var isSignIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
        val signInButton = findViewById<Button>(R.id.signIn)
        signInButton.setOnClickListener {view -> login()}
    }

    private fun login() {
        val users = HashSet<User>()
        users.add(User("home.progr@mail.ru", "hOme123", "Mark", "Makarov", R.drawable.cat))
        if (pref.contains(APP_PREFERENCES_IS_SIGN_IN)) {
            isSignIn = pref.getBoolean(APP_PREFERENCES_IS_SIGN_IN, false)
            email = pref.getString(APP_PREFENCES_EMAIL, "")
        }
        if (intent.getBooleanExtra("signIn", false)) {
            isSignIn = false
        }
        val passwordEditText = findViewById<EditText>(R.id.Password)
        val password = passwordEditText.text.toString()
        val emailEditText = findViewById<EditText>(R.id.Email)
        email = emailEditText.text.toString()
        if (isSignIn) {
            val regexEmail = Regex(pattern = "^([a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9].)*[a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9]@([a-zA-Z][a-zA-Z0-9-]{0,61}[a-zA-Z0-9].)+[a-zA-Z]{2,6}$")
            val regexPassword = Regex(pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$")
            if (email.toString().isNotEmpty() && password.isNotEmpty()) {
                if (regexEmail.containsMatchIn(email.toString()) && regexPassword.containsMatchIn(password)) {
                    if (users.iterator().next().getEmail().equals(email) && users.iterator().next()
                            .getPassword().equals(password)) {
                        val intent = android.content.Intent(this, Main2Activity::class.java).apply {
                            putExtra("email", email)
                            putExtra("name", users.iterator().next().getName())
                            putExtra("lastname", users.iterator().next().getLastName())
                            putExtra("photo", users.iterator().next().getIdPhoto())
                        }
                        startActivity(intent)
                        finish()
                    } else Toast.makeText(this, "It is not equals", Toast.LENGTH_LONG).show()
                } else Toast.makeText(this, "You entered the invalid data", Toast.LENGTH_LONG)
                    .show()
            } else Toast.makeText(this, "One or two fields are empty!", Toast.LENGTH_LONG).show()
        }
        else {
            if (users.iterator().next().getEmail().equals(email) && users.iterator().next()
                    .getPassword().equals(password)) {
                val intent = android.content.Intent(this, Main2Activity::class.java).apply {
                    putExtra("email", users.iterator().next().getEmail())
                    putExtra("name", users.iterator().next().getName())
                    putExtra("lastname", users.iterator().next().getLastName())
                    putExtra("photo", users.iterator().next().getIdPhoto())
                }
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val users = HashSet<User>()
        users.add(User("home.progr@mail.ru", "hOme123", "Mark", "Makarov", R.drawable.cat))
        if (pref.contains(APP_PREFERENCES_IS_SIGN_IN)) {
            isSignIn = pref.getBoolean(APP_PREFERENCES_IS_SIGN_IN, false)
            email = pref.getString(APP_PREFENCES_EMAIL, "")
        }
        if (intent.getBooleanExtra("signIn", false)) {
            isSignIn = false
        }
        val passwordEditText = findViewById<EditText>(R.id.Password)
        val password = passwordEditText.text.toString()
        if (isSignIn) {
            if (users.iterator().next().getEmail().equals(email) && users.iterator().next()
                    .getPassword().equals(password)) {
                val intent = android.content.Intent(this, Main2Activity::class.java).apply {
                    putExtra("email", users.iterator().next().getEmail())
                    putExtra("name", users.iterator().next().getName())
                    putExtra("lastname", users.iterator().next().getLastName())
                    putExtra("photo", users.iterator().next().getIdPhoto())
                }
                startActivity(intent)
                finish()
            }
        }
    }
}
