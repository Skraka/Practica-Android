package com.example.app_practica1

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Monedas : AppCompatActivity() {

    private lateinit var txtCantidad : EditText

    private lateinit var spnConversion : Spinner

    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    private lateinit var txtResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monedas)
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
        spnConversion = findViewById<Spinner>(R.id.spnConversion)
        btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnLimpiar = findViewById<Button>(R.id.btnLimpiar)
        btnCerrar = findViewById<Button>(R.id.btnCerrar)
        txtResultado = findViewById<TextView>(R.id.txtResultado)

        val items = resources.getStringArray(R.array.array_conversiones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        spnConversion.adapter = adapter
    }

    fun eventosClic(){
        var pos :Int = 0
        spnConversion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val item = p0?.getItemAtPosition(p2).toString()
                Toast.makeText(applicationContext, "Seleccionastes $item", Toast.LENGTH_SHORT).show()
                pos = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        btnCalcular.setOnClickListener(View.OnClickListener {
            val dolara = resources.getString(R.string.dolara).toFloat()
            val dolarc = resources.getString(R.string.dolarc).toFloat()
            val euro = resources.getString(R.string.euro).toFloat()

            if (txtCantidad.text.toString().contentEquals("")){
                Toast.makeText(this, "Falto capturar la cantidad", Toast.LENGTH_SHORT).show()
            }
            else {
                var resultado :Float = 0.0f
                var cantidad :Float = 0.0f
                cantidad = txtCantidad.text.toString().toFloat()
                resultado = when (pos) {
                    0 -> cantidad / dolara
                    1 -> cantidad / dolarc
                    2 -> cantidad / euro
                    3 -> cantidad * dolara
                    4 -> cantidad * dolarc
                    5 -> cantidad * euro
                    else -> 0.0f
                }
                txtResultado.text = resultado.toString()
            }
        })
        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtCantidad.setText("")
            txtResultado.text = ""
        })
        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Convertidor de divisas")
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