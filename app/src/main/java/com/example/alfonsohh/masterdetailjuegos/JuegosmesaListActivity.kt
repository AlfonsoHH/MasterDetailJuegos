package com.example.alfonsohh.masterdetailjuegos

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.alfonsohh.masterdetailjuegos.amplificador.Juegosmesa
import com.example.alfonsohh.masterdetailjuegos.amplificador.JuegosmesaContenido
import com.squareup.picasso.Picasso
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
//import kotlinx.android.synthetic.main.activity_main.*

import kotlinx.android.synthetic.main.activity_juegosmesa_list.*
import kotlinx.android.synthetic.main.juegosmesa_list_content.view.*

import kotlinx.android.synthetic.main.juegosmesa_list.*

class juegosmesaListActivity : AppCompatActivity() {

    private var mTwoPane: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juegosmesa_list)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("ludoteca")


        setSupportActionBar(toolbar)
        toolbar.title = title

        //CARGA EL LAYOUT CON EL CONTENEDOR
        if (amplificador_detail_container != null) {
            mTwoPane = true
        }

        //CARGA ESTE LAYOUT EN EL RECYCLED VIEW
        setupRecyclerView(amplificador_list)

        //AQUI EMPIEZA FIREBASE
        JuegosmesaContenido.borrarLista();

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (objj in dataSnapshot.children) {

                    val juegoActual = objj.getValue<Juegosmesa>(Juegosmesa::class.java)

                    JuegosmesaContenido.cargarFirebase(juegoActual)
                    setupRecyclerView(amplificador_list)

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }

        myRef.child("juegos").addValueEventListener(menuListener)

        //ANTES ERA CON UN BOTON, AHORA LO AÑADIMOS SIEMPRE

            /*var juegoInsertar = Juegosmesa("3","Time Stories","Space Cowboys", "2-4", 7,"Un juego narrativo, de temática variada. Viaja por las arenas del tiempo sintiendote transportado a mundo diferentes gracias a una historia y arte sublimes.","https://images-na.ssl-images-amazon.com/images/I/41tfz29dgeL._SY355_.jpg")
            myRef.child("juegos").push().setValue(juegoInsertar)
*/
    }

    //LE PASO LA LISTA MUTABLE
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, JuegosmesaContenido.getJuegosmesaList(), mTwoPane) //mm
    }


    class SimpleItemRecyclerViewAdapter(private val mParentActivity: juegosmesaListActivity,
                                        private val mValues: List<Juegosmesa>,
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
                val item = v.tag as Juegosmesa
                if (mTwoPane) {
                    val fragment = juegosmesaDetailFragment().apply {
                        arguments = Bundle()
                        println(item.id)
                        arguments.putString(juegosmesaDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.amplificador_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, juegosmesaDetailActivity::class.java).apply {
                        putExtra(juegosmesaDetailFragment.ARG_ITEM_ID, item.id)
                    }

                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.juegosmesa_list_content, parent, false)
            return ViewHolder(view)
        }

        //AQUI HAY QUE TOCARLO
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mIdView.text = item.nombre                    //
            holder.mContentView.text = item.editorial              //

            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }

            holder.mImageView.loadUrl(item.urlImagen)

        }

        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.id_text
            val mContentView: TextView = mView.content
            val mImageView: ImageView = mView.imageViewMiniatura
        }
    }
}
