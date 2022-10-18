package net.azarquiel.examenalltricksalc.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface BiciDao{
    //Todas las bicis
    @Query("SELECT * FROM bici")
    fun getAllBicis(): LiveData<List<Bici>>

    @Update
    fun update(bici: Bici)

    //Bicis de una marca
    @Query("select * from bici where marca = :Marca")
    fun getBicisMarca(Marca:Int):LiveData<List<Bici>>

    //Una bici sola
    @Query("SELECT * FROM bici WHERE id = :Id")
    fun getBiciSola(Id:Int):LiveData<List<Bici>>
}

@Dao
interface MarcaDao{
    //Todas las marcas
    @Query("SELECT * FROM marca")
    fun getAllMarcas(): LiveData<List<Marca>>

    @Update
    fun update(marca: Marca)

    //Una marca sola
    @Query("SELECT * FROM marca WHERE id = :Id")
    fun getMarcaSola(Id: Int):Marca

}