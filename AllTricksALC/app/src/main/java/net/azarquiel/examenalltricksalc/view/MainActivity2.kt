package net.azarquiel.examenalltricksalc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.examenalltricksalc.R
import net.azarquiel.examenalltricksalc.model.Bici
import net.azarquiel.examenalltricksalc.model.Marca
import net.azarquiel.examenalltricksalc.viewmodel.BiciViewModel
import net.azarquiel.examenalltricksalc.viewmodel.MarcaViewModel
import net.azarquiel.recyclerviewbicis.adapter.CustomAdapterBicisDescripcion

class MainActivity2 : AppCompatActivity() {

    private lateinit var biciViewModel: BiciViewModel
    private lateinit var marcaViewModel: MarcaViewModel
    private lateinit var rvBicisDatos: RecyclerView
    private lateinit var adapter: CustomAdapterBicisDescripcion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymarcasbici)

        biciViewModel = ViewModelProvider(this).get(BiciViewModel::class.java)
        marcaViewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)
        rvBicisDatos = findViewById(R.id.rvBicisDatos)

        intiRV()
        getData()
        showData()
    }

    private fun intiRV() {
        adapter = CustomAdapterBicisDescripcion(this, R.layout.rowbicidatos)
        rvBicisDatos.adapter = adapter
        rvBicisDatos.layoutManager = LinearLayoutManager(this)
    }

    private fun getData(){
        val marca = intent.getSerializableExtra("marca") as Marca

        setTitle("AllTricks - ${marca.nombre}")

        biciViewModel.getBicisMarca(marca.id).observe(this,{bicis ->
            bicis?.let{ adapter.setBicis(it)}
        })
    }

    private fun showData() {
        adapter = CustomAdapterBicisDescripcion(this, R.layout.rowbicidatos)
        rvBicisDatos.layoutManager = LinearLayoutManager(this)
        rvBicisDatos.adapter = adapter
    }


    fun onClickBiciDatos(v: View){
        val biciPulsada = v.tag as Bici

        val intent = Intent(this, MainActivity3::class.java)
        intent.putExtra("bici", biciPulsada)
        startActivity(intent)
    }
}