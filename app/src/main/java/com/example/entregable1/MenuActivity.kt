package com.example.entregable1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnVerVentas = findViewById<Button>(R.id.btnVerVentas)
        btnVerVentas.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
