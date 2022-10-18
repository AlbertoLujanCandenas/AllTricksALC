package net.azarquiel.examenalltricksalc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.alltricks.util.Util
import net.azarquiel.examenalltricksalc.R
import net.azarquiel.examenalltricksalc.model.Marca
import net.azarquiel.examenalltricksalc.viewmodel.BiciViewModel
import net.azarquiel.examenalltricksalc.viewmodel.MarcaViewModel
import net.azarquiel.recyclerviewmarcas.adapter.CustomAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var rvBicis: RecyclerView
    private lateinit var marcaViewModel: MarcaViewModel
    private lateinit var biciViewModel: BiciViewModel
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        biciViewModel = ViewModelProvider(this).get(BiciViewModel::class.java)
        marcaViewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)
        rvBicis = findViewById(R.id.rvBicisDatos)

        inyecta()
        initRV()
        getData()
        showData()

    }

    private fun initRV() {
        adapter = CustomAdapter(this, R.layout.rowbici)
        rvBicis.adapter = adapter
        rvBicis.layoutManager = LinearLayoutManager(this)

    }

    private fun inyecta() {
        Util.inyecta(this,"alltricks.sqlite")
    }

    private fun getData() {
        marcaViewModel.getAllMarcas().observe(this,{marcas ->
            marcas?.let { adapter.setMarcas(it) }
        })
    }

    private fun showData() {
        adapter = CustomAdapter(this, R.layout.rowbici)
        rvBicis.layoutManager = LinearLayoutManager(this)
        rvBicis.adapter = adapter
    }

    fun onClickBici(v: View){
        val marcaPulsada = v.tag as Marca

        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("marca",marcaPulsada)
        startActivity(intent)
    }
}