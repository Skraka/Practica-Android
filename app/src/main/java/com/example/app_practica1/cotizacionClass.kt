package com.example.app_practica1
import java.io.Serializable
import kotlin.random.Random

class cotizacionClass:Serializable {
    var numCotizacion : Int = 0
    var pagoInicial : Float = 0.0f
    var precio : Float = 0.0f
    var porPagInicial : Float = 0.0f
    var plazos : Int = 0

    constructor() {
        this.numCotizacion = 0
        this.pagoInicial = 0.0f
        this.precio = 0.0f
        this.porPagInicial = 0.0f
        this.plazos = 0
    }
    fun calcularPorcentajeInicial():Float {
        porPagInicial = (pagoInicial/precio)*100
        return porPagInicial
    }
    fun calcularTotalFin():Float{
        return this.precio-this.pagoInicial
    }
    fun calcularPagoMensual():Float{
        return this.calcularTotalFin()/this.plazos
    }
    fun generaFolio() :Int {
        return Random.Default.nextInt(0, 1001)
    }
}