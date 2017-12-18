package com.example.alfonsohh.masterdetailjuegos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.alfonsohh.masterdetailjuegos.amplificador.Juegosmesa
import com.example.alfonsohh.masterdetailjuegos.amplificador.JuegosmesaContenido
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_juegosmesa_detail.*
import kotlinx.android.synthetic.main.juegosmesa_detail.view.*
import kotlinx.android.synthetic.main.juegosmesa_list.*

class juegosmesaDetailFragment : Fragment() {

    //CREAMOS UN OBJETO DE AMPLIFICADOR
    private var mItem: Juegosmesa? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //CREAMOS UN AMPLIFICADOR CONTENIDO
        //var amp = JuegosmesaContenido()
        //amp.loadJuegosmesaList()



        if (arguments.containsKey(ARG_ITEM_ID)) {

            print(arguments.getInt(ARG_ITEM_ID))

            //COGE EL VALOR DE LA URL DEL AMPLIFICADOR QUE HEMOS ESCOGIDO
            mItem = JuegosmesaContenido.getIdJuegosmesa(arguments.getString(ARG_ITEM_ID))
            mItem?.let {
                activity.toolbar_layout?.title = it.nombre

            }
        }




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.juegosmesa_detail, container, false)

        // Show the dummy content as text in a TextView.
        print("ssssss")

        //LO MISMO DE ARRIBA PERO AQUI CARGANDO LA IMAGEN Y SU ENLACE DE ABAJO
        mItem?.let {
            rootView.editorial.text = it.editorial
            rootView.numeroJugadores.text = it.numeroJugadores.toString()
            rootView.complejidad.text = it.complejidad.toString()
            rootView.descripcion.text = it.descripcion
            // rootView.imageView.loadUrl("https://git-scm.com/figures/18333fig0330-tn.png")
            rootView.imageViewProblema.loadUrl(it.urlImagen)
        }



        return rootView
    }

    //CREAMOS UNA FUNCION PARA SOBRESCRIBIR LA FUNCION DE CARGAR HAY QUE AÑADIR LA LIBRERIA PICCASSO AL BUILD.GRADLE
    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }


    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
