package com.example.app_practica1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Grados : AppCompatActivity() {

    private lateinit var txtCantidad : EditText
    private lateinit var txtResultado : TextView
    private lateinit var rdbCel : RadioButton
    private lateinit var rdbFar : RadioButton

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_grados)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        txtCantidad = findViewById<EditText>(R.id.txtCantidad)
        txtResultado = findViewById<TextView>(R.id.txtResultado)
        rdbCel = findViewById<RadioButton>(R.id.rdbCel)
        rdbFar = findViewById<RadioButton>(R.id.rdbFar)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
    }

    fun eventosClic(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            if (txtCantidad.text.toString().contentEquals("")) {
                Toast.makeText(this, "Fallo al capturar cantidad", Toast.LENGTH_SHORT).show()
            }
            else {
                var cantidad = txtCantidad.text.toString().toFloat()
                if (rdbCel.isChecked) {
                    var celsius : Float = 0.0f
                    celsius = (cantidad * 9 / 5) + 32
                    txtResultado.text = celsius.toString()
                }
                if (rdbFar.isChecked) {
                    var fahr : Float = 0.0f
                    fahr = (cantidad - 32) * 5 / 9
                    txtResultado.text = fahr.toString()
                }
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtCantidad.setText("")
            txtResultado.text = ""
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Convertidor de grados")
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