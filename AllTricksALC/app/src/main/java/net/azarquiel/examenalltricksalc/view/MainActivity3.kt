package net.azarquiel.examenalltricksalc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.ims.ImsException
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenalltricksalc.R
import net.azarquiel.examenalltricksalc.model.Bici
import net.azarquiel.examenalltricksalc.viewmodel.BiciViewModel
import net.azarquiel.examenalltricksalc.viewmodel.MarcaViewModel

class MainActivity3 : AppCompatActivity() {
    private lateinit var tvMarca: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var tvDescripcionBici: TextView
    private lateinit var ivBiciConcreto: ImageView
    private lateinit var marcaViewModel: MarcaViewModel
    private lateinit var biciViewModel: BiciViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        biciViewModel = ViewModelProvider(this).get(BiciViewModel::class.java)
        marcaViewModel = ViewModelProvider(this).get(MarcaViewModel::class.java)
        tvMarca = findViewById(R.id.tvMarca)
        tvPrecio = findViewById(R.id.tvPrecio)
        tvDescripcionBici = findViewById(R.id.tvDescripcionBici)
        ivBiciConcreto = findViewById(R.id.ivBiciConcreto)


        showData()
    }

    private fun showData() {
        val bici = intent.getSerializableExtra("bici") as Bici

        tvPrecio.text = bici.precio
        tvDescripcionBici.text = bici.descripcion
        GlobalScope.launch() {
            tvMarca.text = marcaViewModel.getMarcaSola(bici.marca).nombre
        }
        Picasso.get().load("${bici.foto}").into(ivBiciConcreto)
        setTitle("AllTricks")

    }
}