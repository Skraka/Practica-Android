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
import kotlin.math.abs

class Cotizacion : AppCompatActivity() {

    private lateinit var txtCliente : TextView
    private lateinit var txtFolio : TextView
    private lateinit var txtDescripcion : EditText
    private lateinit var txtPagoInicial : EditText
    private lateinit var txtPrecio : EditText

    private lateinit var rdb12 : RadioButton
    private lateinit var rdb24 : RadioButton
    private lateinit var rdb36 : RadioButton
    private lateinit var rdb48 : RadioButton

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    private lateinit var lblPorcentajeInicial : TextView
    private lateinit var lblTotalFin : TextView
    private lateinit var lblPagoMensual : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion)
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
        txtFolio = findViewById<TextView>(R.id.txtFolio)
        txtDescripcion = findViewById<EditText>(R.id.txtDescripcion)
        txtPagoInicial = findViewById<EditText>(R.id.txtPagoInicial)
        txtPrecio = findViewById<EditText>(R.id.txtPrecio)

        rdb12 = findViewById<RadioButton>(R.id.rdb12)
        rdb24 = findViewById<RadioButton>(R.id.rdb24)
        rdb36 = findViewById<RadioButton>(R.id.rdb36)
        rdb48 = findViewById<RadioButton>(R.id.rdb48)

        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)

        lblPorcentajeInicial = findViewById<TextView>(R.id.lblPorcentajeInicial)
        lblTotalFin = findViewById<TextView>(R.id.lblTotalFin)
        lblPagoMensual = findViewById<TextView>(R.id.lblPagoMensual)

        val strCliente : String = intent.getStringExtra("cliente").toString()
        txtCliente.text = strCliente

        val folio : Int = abs(cotizacionClass().generaFolio())
        txtFolio.text = "Folio : $folio"
    }
    fun eventosClic(){
        btnCalcular.setOnClickListener(View.OnClickListener {
            var cotizacion = cotizacionClass()
            if(txtDescripcion.text.toString().contentEquals("") ||
                txtPagoInicial.text.toString().contentEquals("") ||
                txtPrecio.text.toString().contentEquals("")) {
                Toast.makeText(this, "Falto capturar algun dato", Toast.LENGTH_SHORT).show()
            }
            else{
                txtFolio.text = cotizacion.generaFolio().toString()
                cotizacion.pagoInicial = txtPagoInicial.text.toString().toFloat()
                cotizacion.precio = txtPrecio.text.toString().toFloat()

                if(rdb12.isChecked) cotizacion.plazos = 12
                if(rdb24.isChecked) cotizacion.plazos = 24
                if(rdb36.isChecked) cotizacion.plazos = 36
                if(rdb48.isChecked) cotizacion.plazos = 48

                lblPorcentajeInicial.text = "Porcentaje de pago inicial: " + cotizacion.calcularPorcentajeInicial().toString()
                lblTotalFin.text = "Total a financiar: " + cotizacion.calcularTotalFin()
                lblPagoMensual.text = "Pago mensual: " + cotizacion.calcularPagoMensual()
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            val folio : Int = abs(cotizacionClass().generaFolio())
            txtFolio.text = "Folio : $folio"

            txtDescripcion.setText("")
            txtPagoInicial.setText("")
            txtPrecio.setText("")

            rdb12.isChecked = true

            lblPorcentajeInicial.text = "Porcentaje de pago inicial: "
            lblTotalFin.text = "Total a financiar: "
            lblPagoMensual.text = "Pago mensual: "
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Cotizacion")
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