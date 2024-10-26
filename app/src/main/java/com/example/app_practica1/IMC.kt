package com.example.app_practica1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IMC : AppCompatActivity() {

    private lateinit var txtAltura : EditText
    private lateinit var txtPeso : EditText
    private lateinit var txtResultado : TextView
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtAltura = findViewById<EditText>(R.id.txtAltura)
        txtPeso = findViewById<EditText>(R.id.txtPeso)
        txtResultado = findViewById<EditText>(R.id.txtResultado)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
    }

    fun eventosClic(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            if(txtPeso.text.toString().contentEquals("") || txtAltura.text.toString().contentEquals("")) {
                Toast.makeText(this,"Falto capturar informacion", Toast.LENGTH_SHORT).show()
            }
            else{
                var peso : Float = txtPeso.text.toString().toFloat()
                var altura : Float = txtAltura.text.toString().toFloat()
                var imc : Float = peso/(altura*altura)
                txtResultado.text = imc.toString()
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtResultado.text = ""
            txtPeso.setText("")
            txtAltura.setText("")
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("IMC")
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