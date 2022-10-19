package net.alberapps.examenavesalc.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by pacopulido on 23/02/2021.
 */

object WebAccess {

    val pajarosService : PajarosService by lazy {

        //Parsea el JSON en objetos
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://www.ies-azarquiel.es/paco/apiaves/")
            .build()

        return@lazy retrofit.create(PajarosService::class.java)
    }
}