package net.azarquiel.model

import java.io.Serializable

/**
 * Created by Paco Pulido on 14/02/2019.
 */

data class Zona (
    var id:Long,
    var nombre:String,
    var localizacion: String,
    var formaciones_principales:String,
    var presentacion:String,
    var geom_lat:Double,
    var geom_lon:Double
): Serializable

data class Recurso(
    var id:Long,
    var zona:Long,
    var titulo :String,
    var url:String
):Serializable

data class Usuario(
    var id:Long,
    var nick:String,
    var pass:String
):Serializable

data class Comentario(
    var id:Long,
    var nick:String,
    var recurso:Long,
    var fecha:String,
    var comentario:String
):Serializable

data class Respuesta (
    var zonas:List<Zona>,
    var recursos:List<Recurso>,
    var recurso:Recurso,
    var comentarios:List<Comentario>,
    var comentario:Comentario,
    var usuario: Usuario
)

