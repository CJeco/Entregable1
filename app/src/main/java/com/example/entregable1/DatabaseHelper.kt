package com.example.entregable1

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import java.io.File
import java.io.FileOutputStream

class DatabaseHelper (private val context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    companion object{
        private const val DB_NAME = "todobarato.db"
        private const val DB_VERSION = 1
    }

    init {
        copiarBaseDeDatos()
    }

    private fun copiarBaseDeDatos() {
        val dbPath = context.getDatabasePath(DB_NAME)
        if (!dbPath.exists()) {
            dbPath.parentFile?.mkdirs()
            context.assets.open(DB_NAME).use { input ->
                FileOutputStream(dbPath).use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

        override fun onCreate(db: SQLiteDatabase?){

        }
        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int){

        }

        fun obtenerTodasLasVentas(): Cursor {
            val db = readableDatabase
            return db.rawQuery("SELECT codigo, nombre, precio, cantidad, tipo, fecha_venta FROM ventas", null)

        }

        fun buscarVentaPorCodigo(codigo: String): Cursor {
            val db = readableDatabase
            return db.rawQuery("SELECT * FROM ventas WHERE codigo = ?", arrayOf(codigo))
        }

        fun obtenerVentasPorTipo(tipo: String): Cursor {
            val db = readableDatabase
            return db.rawQuery("SELECT * FROM ventas WHERE tipo = ?", arrayOf(tipo))
        }

    }


