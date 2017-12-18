package com.example.alfonsohh.masterdetailjuegos.amplificador

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by luissancar on 03/11/2017.
 */
object JuegosmesaContenido {

    private val juegosmesaList: MutableList<Juegosmesa> = ArrayList()


    public  fun getJuegosmesaList(): MutableList<Juegosmesa> {
        return juegosmesaList
    }

    public fun getIdJuegosmesa(id:String): Juegosmesa {
        for (obbj in juegosmesaList) {
            if (obbj.id == id) {
                return obbj
            }
        }
        return juegosmesaList.get(0)
    }

    public fun cargarFirebase(juego: Juegosmesa){
        juegosmesaList.add(juego)
        Log.i("hola",juego.editorial)
    }

    public fun borrarLista(){
        juegosmesaList.clear();
    }
}