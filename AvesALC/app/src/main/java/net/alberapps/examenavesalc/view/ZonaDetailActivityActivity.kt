package net.alberapps.examenavesalc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.alberapps.examenavesalc.R
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.model.Zona

class ZonaDetailActivityActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var tvDescripcionPresentacion: TextView
    private lateinit var zona: Zona
    private lateinit var fabInfo: FloatingActionButton
    private lateinit var tvFormaciones: TextView
    private lateinit var tvDescripcionFormacion: TextView
    private lateinit var tvccaapos: TextView
    private lateinit var tvDetailZona: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zona_detail_activity)

        zona = intent.getSerializableExtra("zona") as Zona

        tvDetailZona = findViewById(R.id.tvDetailZona)
        tvccaapos = findViewById(R.id.tvccaapos)
        tvFormaciones = findViewById(R.id.tvFormaciones)
        tvDescripcionFormacion = findViewById(R.id.tvDescripcionFormacion)
        tvDescripcionPresentacion = findViewById(R.id.tvDescripcionPresentacion)
        fabInfo = findViewById(R.id.fabInfo)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initDatosZona()

        fabInfo.setOnClickListener { view ->
            showInfo()
        }
    }

    private fun initDatosZona() {
        tvDetailZona.text = zona.nombre
        tvccaapos.text = zona.localizacion + " " + zona.geom_lat + " " + zona.geom_lon
        tvDescripcionFormacion.text = zona.formaciones_principales
        tvDescripcionPresentacion.text = zona.presentacion
    }

    private fun showInfo() {
        val intent = Intent(this, RecursosActivity::class.java)
        intent.putExtra("zona", zona)
        startActivity(intent)
    }
}