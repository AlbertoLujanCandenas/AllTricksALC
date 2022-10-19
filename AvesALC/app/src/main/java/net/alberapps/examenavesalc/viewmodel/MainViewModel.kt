package net.azarquiel.caravanretrofit.viewmodel

import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.alberapps.examenavesalc.api.MainRepository
import net.azarquiel.model.Comentario
import net.azarquiel.model.Recurso
import net.azarquiel.model.Usuario
import net.azarquiel.model.Zona

// ……

/**
 * Created by pacopulido on 23/02/2021.
 */
class MainViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getZonas(): MutableLiveData<List<Zona>> {
        val games = MutableLiveData<List<Zona>>()
        GlobalScope.launch(Main) {
            games.value = repository.getZonas()
        }
        return games
    }

    fun getUserByNickAndPass(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioresponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioresponse.value = repository.getUserByNickAndPass(usuario)
        }
        return usuarioresponse
    }

    fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioresponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioresponse.value = repository.saveUsuario(usuario)
        }
        return usuarioresponse
    }

    fun getRecursosByZona(idrecurso:Long): MutableLiveData<List<Recurso>>{
        val recursos = MutableLiveData<List<Recurso>>()
        GlobalScope.launch(Main){
            recursos.value = repository.getRecursoByZona(idrecurso)
        }
        return recursos
    }

    fun getComentariosByRecurso(idrecurso: Long):MutableLiveData<List<Comentario>>{
        val comentarios = MutableLiveData<List<Comentario>>()
        GlobalScope.launch(Main){
            comentarios.value = repository.getComentariosByRecurso(idrecurso)
        }
        return comentarios
    }



//
    fun subirNuevoTema(recurso: Recurso):MutableLiveData<Recurso>{
        val recursoResponse = MutableLiveData<Recurso>()
        GlobalScope.launch(Main){
            recursoResponse.value = repository.nuevoComentario(recurso)
        }
        return recursoResponse
    }
}
