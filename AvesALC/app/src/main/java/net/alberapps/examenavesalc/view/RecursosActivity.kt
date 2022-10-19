package net.alberapps.examenavesalc.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.alberapps.examenavesalc.R
import net.azarquiel.adapter.RecursosAdapter
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.model.Recurso
import net.azarquiel.model.Zona

class RecursosActivity : AppCompatActivity() {
    private lateinit var recurso: List<Recurso>
    private lateinit var zona: Zona
    private lateinit var adapter: RecursosAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var rvRecursos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursos)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        rvRecursos = findViewById(R.id.rvRecursos)


        zona = intent.getSerializableExtra("zona") as Zona
        initRV()
        getRecursos()
    }

    private fun initRV() {
        adapter = RecursosAdapter(this, R.layout.row_recurso)
        rvRecursos.adapter = adapter
        rvRecursos.layoutManager = LinearLayoutManager(this)
    }

    private fun getRecursos() {
        viewModel.getRecursosByZona(zona.id).observe(this){ it ->
        it?.let{
            recurso = it
            showRecursos()
            }
        }
    }

    private fun showRecursos() {
        adapter.setRecursos(recurso)
    }

    fun onClickRecurso(v: View){
        var recurso = v.tag as Recurso
        val intent = Intent(this, PajaroActivity::class.java)
        intent.putExtra("recurso", recurso)
        startActivity(intent)
    }
}