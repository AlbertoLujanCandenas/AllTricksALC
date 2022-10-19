package net.alberapps.examenavesalc.api

import net.azarquiel.model.Comentario
import net.azarquiel.model.Recurso
import net.azarquiel.model.Usuario
import net.azarquiel.model.Zona

class MainRepository() {
    val service = WebAccess.pajarosService

//    //Siempre poner el suspend para el await
    suspend fun getZonas(): List<Zona> {
        val webResponse = service.getZonas().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.zonas
        }
        return emptyList()
    }

    suspend fun getUserByNickAndPass(usuario: Usuario): Usuario? {
        var usuarioresponse: Usuario? = null
        val webResponse = service.getUserByNickAndPass(usuario.nick,usuario.pass).await()
        if (webResponse.isSuccessful) {
            usuarioresponse = webResponse.body()!!.usuario
        }
        return usuarioresponse
    }

    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        var usuarioresponse:Usuario? = null
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            usuarioresponse = webResponse.body()!!.usuario
        }
        return usuarioresponse
    }

    suspend fun getRecursoByZona(idrecurso: Long): List<Recurso>{
        val webResponse = service.getRecursosByZona(idrecurso).await()
        if(webResponse.isSuccessful){
            return webResponse.body()!!.recursos
        }
        return emptyList()
    }

    suspend fun getComentariosByRecurso(id:Long): List<Comentario>{
        val webResponse = service.getComentariosByRecurso(id).await()
        if(webResponse.isSuccessful){
            return webResponse.body()!!.comentarios
        }
        return emptyList()
    }

    suspend fun nuevoComentario(recurso: Recurso): Recurso?{
        var temaresponse: Recurso? = null
        val webResponse = service.nuevoComentario(recurso).await()
        if(webResponse.isSuccessful){
            return webResponse.body()!!.recurso
        }
        return temaresponse
    }
}