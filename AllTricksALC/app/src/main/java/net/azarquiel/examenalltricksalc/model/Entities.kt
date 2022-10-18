package net.azarquiel.examenalltricksalc.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "bici",
    foreignKeys = [ForeignKey(entity = Marca::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("marca"))])
    data class Bici(
        @PrimaryKey
        var id: Int,
        var foto: String,
        var marca: Int,
        var descripcion: String,
        var precio: String,
        var fav: Int):Serializable

@Entity(tableName = "marca")
data class Marca(
    @PrimaryKey
    var id: Int,
    var nombre: String):Serializable
