package net.azarquiel.recyclerviewbicis.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.examenalltricksalc.R
import net.azarquiel.examenalltricksalc.model.Bici

/**
 * Created by pacopulido on 9/10/18.
 */

class CustomAdapterBicisDescripcion(val context: Context,
                                    val layout: Int
                    ) : RecyclerView.Adapter<CustomAdapterBicisDescripcion.ViewHolder>() {

    private var dataList: List<Bici> = emptyList()

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

    internal fun setBicis(bicis: List<Bici>) {
        this.dataList = bicis
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Bici){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvBici = itemView.findViewById(R.id.tvDescripcion) as TextView
            val ivBici =itemView.findViewById(R.id.ivBiciReal) as ImageView

            tvBici.text = dataItem.descripcion

            Picasso.get().load("${dataItem.foto}").into(ivBici)

            itemView.tag = dataItem

        }
    }
}