package net.alberapps.examenavesalc.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import net.alberapps.examenavesalc.R
import net.alberapps.examenavesalc.databinding.ActivityMainBinding
import net.azarquiel.adapter.ZonasAdapter
import net.azarquiel.caravanretrofit.viewmodel.MainViewModel
import net.azarquiel.model.Usuario
import net.azarquiel.model.Zona

class MainActivity : AppCompatActivity() {

    private lateinit var shareP: SharedPreferences
    private lateinit var zonas: List<Zona>
    private lateinit var adapter: ZonasAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var rvZonas: RecyclerView
    private lateinit var toolbar: Toolbar
    private var usuario: Usuario?=null
    private var titulo: String= "ExamenAvesALC "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shareP = getSharedPreferences("datos", Context.MODE_PRIVATE)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        rvZonas = findViewById(R.id.rvzonas)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        getUSuarioSH()
        initRV()
        getZonas()

    }

    private fun getUSuarioSH() {
        val usuarioTXT = shareP.getString("usuario","nosta")
        if(!usuarioTXT.equals("nosta")){
            usuario = Gson().fromJson(usuarioTXT, Usuario::class.java)
            title = "$titulo - ${usuario!!.nick}"
        }
    }

    private fun initRV() {
        adapter = ZonasAdapter(this, R.layout.row_zona)
        rvZonas.adapter = adapter
        rvZonas.layoutManager = LinearLayoutManager(this)
    }

    private fun getZonas(){
        viewModel.getZonas().observe(this) { it ->
            it?.let {
                zonas = it
                showZonas()
            }
        }
    }

    private fun showZonas() {
        adapter.setZonas(zonas)
    }

    fun onClickZona(v: View){
        var zona = v.tag as Zona
        val intent = Intent(this, ZonaDetailActivityActivity::class.java)
        intent.putExtra("zona", zona)
        val intentUsuarioPajaro = Intent(this, PajaroActivity::class.java)
        intentUsuarioPajaro.putExtra("usuario",usuario)
        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            //Dices que tiene que hacer cada accion del menu
            R.id.action_register -> {
                dialogoRegister()
                true
                //Prk se pone true???
            }
            R.id.action_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        removeUsuarioSH()
        msg("Cerrando sesión")
    }

    private fun removeUsuarioSH() {
        val editor = shareP.edit()
        editor.remove("usuario")
        editor.commit()
        usuario = null
        title = titulo
    }

    private fun msg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun dialogoRegister() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Login/Register")
        val ll = LinearLayout(this)
        ll.setPadding(30,30,30,30)
        ll.orientation = LinearLayout.VERTICAL

        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        lp.setMargins(0,50,0,50)

        val textInputLayoutNick = TextInputLayout(this)
        textInputLayoutNick.layoutParams = lp
        val etnick = EditText(this)
        etnick.setPadding(0, 80, 0, 80)
        etnick.textSize = 20.0F
        etnick.hint = "Nick"
        textInputLayoutNick.addView(etnick)

        val textInputLayoutPass = TextInputLayout(this)
        textInputLayoutPass.layoutParams = lp
        val etpass = EditText(this)
        etpass.setPadding(0, 80, 0, 80)
        etpass.textSize = 20.0F
        etpass.hint = "Pass"
        textInputLayoutPass.addView(etpass)

        ll.addView(textInputLayoutNick)
        ll.addView(textInputLayoutPass)

        builder.setView(ll)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            val usuario = Usuario(0,etnick.text.toString(), etpass.text.toString())
            loginregister(usuario)
        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
        }

        builder.show()
    }

    private fun loginregister(usuario: Usuario) {
        viewModel.getUserByNickAndPass(usuario).observe(this, Observer { it ->
            if (it==null) {
                viewModel.saveUsuario(usuario).observe(this, Observer { it ->
                    it?.let {
                        this.usuario = it
                        saveUsuarioSH(it)
                        msg("Usuario creado...")
                    }
                })
            }
            else {
                this.usuario = it
                saveUsuarioSH(it)
                msg("Login OK....")
            }
        })
    }

    private fun saveUsuarioSH(usuario: Usuario) {
        val editor = shareP.edit()
        editor.putString("usuario", Gson().toJson(usuario))
        editor.commit()
        title = "$titulo - ${usuario.nick}"
    }
}