package com.example.entregable1

import android.graphics.Color
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView


class VentaAdapter (context: Context, private val listaVentas: List<Venta>) :
    ArrayAdapter<Venta>(context, 0, listaVentas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_venta, parent, false)
        val venta = listaVentas[position]

        val tvNombreCodigo = view.findViewById<TextView>(R.id.tvNombreCodigo)
        val tvDetalles = view.findViewById<TextView>(R.id.tvDetalles)
        val tvTipo = view.findViewById<TextView>(R.id.tvTipo)
        val tvFecha = view.findViewById<TextView>(R.id.tvFecha)

        tvNombreCodigo.text = "${venta.nombre} (${venta.codigo})"
        tvDetalles.text = "Precio: S/${venta.precio} | Cantidad: ${venta.cantidad}"
        tvFecha.text = "Fecha: ${venta.fecha}"

        tvTipo.text ="Tipo: ${venta.tipo.uppercase()}"
        if(venta.tipo.equals("boleta",ignoreCase = true)){
            tvTipo.setTextColor(Color.parseColor("#2E7D32"))
        }else if(venta.tipo.equals("factura",ignoreCase = true)){
            tvTipo.setTextColor(Color.parseColor("#C62828"))
        }

        return view

    }
}