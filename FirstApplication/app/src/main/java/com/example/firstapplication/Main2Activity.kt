package com.example.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.info.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info)
        val exitButton = findViewById<Button>(R.id.exit)
        if (intent != null) {
            Photo.setImageResource(intent.getIntExtra("photo", 0))
            EmailView.text = intent.getStringExtra("email")
            Name.text = intent.getStringExtra("name")
            Lastname.text = intent.getStringExtra("lastname")
        }
        exitButton.setOnClickListener {view -> onClick(view)}
    }

    private fun onClick(view: View) {
        val intent = android.content.Intent(this, MainActivity::class.java).apply {
            putExtra("signIn", true)
        }
        startActivity(intent)
        finish()
    }
}
