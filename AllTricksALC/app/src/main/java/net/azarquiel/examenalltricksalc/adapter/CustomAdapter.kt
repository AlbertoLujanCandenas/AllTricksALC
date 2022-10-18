package net.azarquiel.recyclerviewmarcas.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.examenalltricksalc.R
import net.azarquiel.examenalltricksalc.model.Marca

/**
 * Created by pacopulido on 9/10/18.
 */

class CustomAdapter(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dataList: List<Marca> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setMarcas(marcas: List<Marca>) {
        this.dataList = marcas
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Marca){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvMarca = itemView.findViewById(R.id.tvDescripcion) as TextView
            val ivBici =itemView.findViewById(R.id.ivBiciReal) as ImageView

            tvMarca.text = dataItem.nombre

            itemView.tag = dataItem
        }

    }
}