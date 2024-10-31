package com.example.app_practica1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Hola : AppCompatActivity() {

    private lateinit var txtNombre: EditText
    private lateinit var txtSaludo: TextView
    private lateinit var btnPulsar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hola)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarComponentes(){
        txtNombre = findViewById<EditText>(R.id.txtNombreFL)!!
        txtSaludo = findViewById<TextView>(R.id.txtSaludo)!!
        btnPulsar = findViewById<Button>(R.id.btnPulsar)!!
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)!!
        btnSalir = findViewById<Button>(R.id.btnSalir)!!
    }

    @SuppressLint("SetTextI18n")
    fun eventosClic(){
        btnPulsar.setOnClickListener(View.OnClickListener {
            if (txtNombre.text.toString().contentEquals("")){
                Toast.makeText(this, "Falto captura de nombre", Toast.LENGTH_SHORT).show()
            }
            else {
                val strNobre: String = txtNombre.text.toString()
                txtSaludo.text = "Hola $strNobre Como estas?"
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtSaludo.text = ""
            txtNombre.setText("")
        })
        btnSalir.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

}