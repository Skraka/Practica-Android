package com.example.app_practica1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class menuActivity : AppCompatActivity() {

    private lateinit var crvHola :CardView
    private lateinit var crvImc :CardView
    private lateinit var crvConvertidor :CardView
    private lateinit var crvMoneda :CardView
    private lateinit var crvCotizacion :CardView
    private lateinit var crvSalir :CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun iniciarComponentes(){
        crvHola = findViewById<CardView>(R.id.crvHola)
        crvImc = findViewById<CardView>(R.id.crvImc)
        crvConvertidor = findViewById<CardView>(R.id.crvConvertidor)
        crvMoneda = findViewById<CardView>(R.id.crvMoneda)
        crvCotizacion = findViewById<CardView>(R.id.crvCotizacion)
        crvSalir = findViewById<CardView>(R.id.crvSalir)
    }

    fun eventosClic(){
        crvHola.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Hola::class.java)
            startActivity(intent)
        })
        crvImc.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,IMC::class.java)
            startActivity(intent)
        })
        crvConvertidor.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,Grados::class.java)
            startActivity(intent)
        })
        //asd
        crvSalir.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Deseas Salir?")
            builder.setPositiveButton("Aceptar"){
                    dialog ,whitch ->
                finishAffinity()
            }
            builder.setNegativeButton("Cancelar"){
                    dialog ,whitch ->
            }
            builder.show()
        })
    }
}