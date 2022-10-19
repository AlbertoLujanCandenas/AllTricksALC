package net.alberapps.examenavesalc.view

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.alberapps.examenavesalc.R
import net.azarquiel.adapter.ComentariosAdapter
import net.azarquiel.adapter.RecursosAdapter
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.model.Comentario
import net.azarquiel.model.Recurso
import net.azarquiel.model.Usuario
import org.w3c.dom.Text

class PajaroActivity : AppCompatActivity() {
    private val titulo: String = "ExamenAvesALC"
    private var usuario: Usuario? = null
    private lateinit var shareP: SharedPreferences
    private lateinit var fabAñadirComentario: FloatingActionButton
    private lateinit var viewModel: MainViewModel
    private lateinit var comentarios: List<Comentario>
    private lateinit var rvComentario: RecyclerView
    private lateinit var adapter: ComentariosAdapter
    private lateinit var ivPajaro: ImageView
    private lateinit var tvNombrePajaro: TextView
    private lateinit var recurso: Recurso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pajaro)

        tvNombrePajaro = findViewById(R.id.tvNombrePajaro2)
        ivPajaro = findViewById(R.id.ivPajaroComentario2)
        rvComentario = findViewById(R.id.rvComentarios)
        fabAñadirComentario = findViewById(R.id.fabAñadirComentario)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        GlobalScope.launch(Main) {
            Picasso.get().load("${recurso.url}").into(ivPajaro)
        }
        recurso = intent.getSerializableExtra("recurso") as Recurso
        usuario = intent.getSerializableExtra("usuario") as Usuario?

        tvNombrePajaro.text =recurso.titulo

        shareP = getSharedPreferences("datos", Context.MODE_PRIVATE)

        fabAñadirComentario.setOnClickListener { view ->
            dialogComentario()
        }

        getUSuarioSH()
        initRV()
        getComentarios()
    }

    private fun getUSuarioSH() {
        val usuarioTXT = shareP.getString("usuario","nosta")
        if(!usuarioTXT.equals("nosta")){
            usuario = Gson().fromJson(usuarioTXT, Usuario::class.java)
            title = "$titulo - ${usuario!!.nick}"
        }
    }

    private fun initRV() {
        adapter = ComentariosAdapter(this, R.layout.row_pajaro)
        rvComentario.adapter = adapter
        rvComentario.layoutManager = LinearLayoutManager(this)
    }

    private fun getComentarios() {
        viewModel.getComentariosByRecurso(recurso.id).observe(this){ it ->
            it?.let{
                comentarios = it
                showComentarios()
            }
        }
    }

    private fun showComentarios() {
        adapter.setComentarios(comentarios)
    }

    private fun dialogComentario(){
        if(usuario==null){
            Toast.makeText(this, "Debes loguearte para poder insertar un comentario", Toast.LENGTH_SHORT).show()
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Añadir un comentario al recurso")
            val ll = LinearLayout(this)
            ll.setPadding(30)
            ll.orientation = LinearLayout.VERTICAL

            val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            lp.setMargins(0,50,0,50)

            val textInputLayoutTema = TextInputLayout(this)
            textInputLayoutTema.layoutParams = lp
            val etComentario = EditText(this)
            etComentario.setPadding(0, 80, 0, 80)
            etComentario.textSize = 20.0F
            etComentario.hint = "Comentario"
            textInputLayoutTema.addView(etComentario)

            ll.addView(textInputLayoutTema)

            builder.setView(ll)

            builder.setPositiveButton("Aceptar") { dialog, which ->
                nuevoRecurso(etComentario.text.toString())
            }

            builder.setNegativeButton("Cancelar") { dialog, which ->
            }

            builder.show()
        }
    }

    private fun nuevoRecurso(comentario: String) {
        val comentarioAñadir = Comentario(0,"${usuario?.id}",recurso.id,"",comentario)

//        viewModel.subirNuevoTema(comentarioAñadir).observe(this) { it ->
//            it?.let {
//                getComentarios()
//                showComentarios()
//            }
//        }
    }
}