package com.example.entregable1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val listViewVentas = findViewById<ListView>(R.id.ListViewVentas)
        val dbHelper = DatabaseHelper(this)
        val listaVentasTexto = ArrayList<String>()
        val cursor = dbHelper.obtenerTodasLasVentas()

        if (cursor.moveToFirst()){
            do {
                val codigo = cursor.getString(cursor.getColumnIndexOrThrow("codigo"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"))
                val cantidad = cursor.getInt(cursor.getColumnIndexOrThrow("cantidad"))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha_venta"))
                val item = "[$codigo] $nombre\nPrecio: S/$precio | Cant: $cantidad | Tipo: $tipo\nFecha: $fecha"
                listaVentasTexto.add(item)
            }while (cursor.moveToNext())
        }else {
            Toast.makeText(this, "No se encontraron ventas", Toast.LENGTH_LONG).show()
        }
        cursor.close()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaVentasTexto)
        listViewVentas.adapter = adapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}