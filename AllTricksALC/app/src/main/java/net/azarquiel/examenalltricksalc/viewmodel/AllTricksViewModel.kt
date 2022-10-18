package net.azarquiel.examenalltricksalc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.examenalltricksalc.model.Bici
import net.azarquiel.examenalltricksalc.model.BiciRepository
import net.azarquiel.examenalltricksalc.model.Marca
import net.azarquiel.examenalltricksalc.model.MarcaRepository

class  BiciViewModel (application: Application) : AndroidViewModel(application){

    private var repository: BiciRepository = BiciRepository(application)

    fun getAllBicis(): LiveData<List<Bici>>{
        return repository.getAllBicis()
    }

    fun update(bici: Bici){
        GlobalScope.launch() {
            repository.update(bici)
        }
    }

    fun getBicisMarca(Marca:Int): LiveData<List<Bici>>{
        return repository.getBicisMarca(Marca)
    }
    
    fun getBiciSola(Id:Int): LiveData<List<Bici>>{
        return repository.getBiciSola(Id)
    }
}

class MarcaViewModel (application: Application): AndroidViewModel(application){

    private var repository: MarcaRepository = MarcaRepository(application)

    fun getAllMarcas(): LiveData<List<Marca>>{
        return  repository.getAllMarcas()
    }

    fun update(marca: Marca){
        GlobalScope.launch() {
            repository.update(marca)
        }
    }

    fun getMarcaSola(Id:Int): Marca{
        return repository.getMarcaSola(Id)
    }
}