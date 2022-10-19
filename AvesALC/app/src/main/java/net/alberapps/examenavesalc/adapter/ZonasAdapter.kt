package net.azarquiel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.alberapps.examenavesalc.R
import net.azarquiel.model.Zona


/**
 * Created by pacopulido on 9/10/18.
 */
class ZonasAdapter(val context: Context,
                   val layout: Int
                    ) : RecyclerView.Adapter<ZonasAdapter.ViewHolder>() {

    private var dataList: List<Zona> = emptyList()

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

    internal fun setZonas(zonas: List<Zona>) {
        this.dataList = zonas
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Zona){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvzona = itemView.findViewById(R.id.tvZona) as TextView
            val tvccaa = itemView.findViewById(R.id.tvNick) as TextView

            tvzona.text = dataItem.nombre
            tvccaa.text = dataItem.localizacion

            // foto de internet a traves de Picasso
            //Picasso.get().load("http://cdn.akamai.steamstatic.com/steam/apps/${dataItem.appid}/header.jpg").into(ivrow)

            itemView.tag = dataItem

        }

    }
}