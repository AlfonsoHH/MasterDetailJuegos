package com.example.alfonsohh.masterdetailjuegos.amplificador

/**
 * Created by luissancar on 03/11/2017.
 */
/*data class Juegosmesa(val id: String, val nombre: String, val editorial: String,val numeroJugadores: String,val complejidad: Int, val descripcion: String, val urlImagen: String) {

}*/
/*
data class Juegosmesa(val id: String, val nombre: String, val editorial: String,val numeroJugadores: String, val complejidad: Int, val descripcion: String, val urlImagen: String){
}
*/
data class Juegosmesa(
        val id: String = "",
        val nombre: String = "",
        val editorial: String = "",
        val numeroJugadores: String = "",
        val complejidad: Int = 0,
        val descripcion: String = "",
        var urlImagen: String = "")