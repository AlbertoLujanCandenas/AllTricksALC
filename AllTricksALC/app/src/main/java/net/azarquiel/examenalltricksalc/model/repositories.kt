package net.azarquiel.examenalltricksalc.model

import android.app.Application
import androidx.lifecycle.LiveData

class BiciRepository(application: Application) {

    val biciDao = AllTricksDB.getDatabase(application).BiciDao()

    // select
    fun getAllBicis(): LiveData<List<Bici>>{
        return biciDao.getAllBicis()
    }

    //update
    fun update(bici: Bici){
        biciDao.update(bici)
    }

    //Bicis de una marca
    fun getBicisMarca(Marca:Int): LiveData<List<Bici>>{
        return biciDao.getBicisMarca(Marca)
    }

    //Una bici sola
    fun getBiciSola(Id:Int): LiveData<List<Bici>>{
        return biciDao.getBiciSola(Id)
    }

}
class MarcaRepository(application: Application) {

    val marcaDao = AllTricksDB.getDatabase(application).MarcaDao()

    //select
    fun getAllMarcas(): LiveData<List<Marca>> {
        return marcaDao.getAllMarcas()
    }

    //update
    fun update(marca: Marca) {
        return marcaDao.update(marca)
    }

    fun getMarcaSola(Id:Int):Marca{
       return marcaDao.getMarcaSola(Id)
    }
}

