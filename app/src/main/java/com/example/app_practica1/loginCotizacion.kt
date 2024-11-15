package com.example.app_practica1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class loginCotizacion : AppCompatActivity() {

    private lateinit var txtCliente : TextView
    private lateinit var btnRegresar : Button
    private lateinit var btnIngresar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_cotizacion)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarComponentes(){
        txtCliente = findViewById<TextView>(R.id.txtCliente)
        btnRegresar = findViewById<Button>(R.id.btnRegresar)
        btnIngresar = findViewById<Button>(R.id.btnIngresar)
    }
    fun eventosClic(){
        btnIngresar.setOnClickListener(View.OnClickListener {
            if(txtCliente.text.toString().contentEquals("")) {
                Toast.makeText(this, "Fallo al capturar el nombre del cliente", Toast.LENGTH_SHORT).show()
                txtCliente.requestFocus()
            }
            else {
                val intent = Intent(this,Cotizacion::class.java)
                intent.putExtra("cliente", txtCliente.text.toString())
                startActivity(intent)
            }
        })
        btnRegresar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("")
            builder.setMessage("Deseas Salir?")
            builder.setPositiveButton("Aceptar"){
                    dialog ,whitch ->
                finish()
            }
            builder.setNegativeButton("Cancelar"){
                    dialog ,whitch ->
            }
            builder.show()
        })
    }
}