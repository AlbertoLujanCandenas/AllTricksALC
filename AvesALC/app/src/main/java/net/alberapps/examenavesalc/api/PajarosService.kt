package net.alberapps.examenavesalc.api

import kotlinx.coroutines.Deferred
import net.azarquiel.model.Recurso
import net.azarquiel.model.Respuesta
import net.azarquiel.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface PajarosService {

    // No necesita nada para trabajar
    @GET("zonas")
    fun getZonas(): Deferred<Response<Respuesta>>

    @GET("zona/{idzona}/recursos")
    fun getRecursosByZona(@Path("idzona") idzona:Long): Deferred<Response<Respuesta>>

    @GET("recurso/{idrecurso}/comentarios")
    fun getComentariosByRecurso(@Path("idrecurso") idrecurso:Long): Deferred<Response<Respuesta>>


    //tal vez haya que cambiarlo a Path(value)
    @POST("recurso/{idrecurso}/comentario")
    fun nuevoComentario(@Body idrecurso: Recurso): Deferred<Response<Respuesta>>

    @GET("usuario")
    fun getUserByNickAndPass(
        @Query("nick") nick: String,
        @Query("pass") pass: String): Deferred<Response<Respuesta>>

    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Respuesta>>
}