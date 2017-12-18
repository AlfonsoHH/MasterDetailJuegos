package com.example.alfonsohh.masterdetailjuegos

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_juegosmesa_detail.*

/**
 * An activity representing a single amplificador detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [juegosmesaListActivity].
 */
class juegosmesaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juegosmesa_detail)
        setSupportActionBar(detail_toolbar)


        imageView.loadUrl("http://inticreates.com/wp-content/uploads/2017/05/TMG-2017-Logo-500.png")


        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //ESTO TAMPOCO SE TOCA
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val arguments = Bundle()
            arguments.putString(juegosmesaDetailFragment.ARG_ITEM_ID,
                    intent.getStringExtra(juegosmesaDetailFragment.ARG_ITEM_ID))
            val fragment = juegosmesaDetailFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.amplificador_detail_container, fragment)
                    .commit()
        }



    }


    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }


    //AL PULSAR EN EL DETAIL SE VA A CARGAR EL OBJETO SELECCIONADO
    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {

                    navigateUpTo(Intent(this, juegosmesaListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
