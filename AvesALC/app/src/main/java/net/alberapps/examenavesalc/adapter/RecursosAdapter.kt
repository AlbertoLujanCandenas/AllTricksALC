package net.azarquiel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.alberapps.examenavesalc.R
import net.azarquiel.model.Recurso


/**
 * Created by pacopulido on 9/10/18.
 */
class RecursosAdapter(val context: Context,
                      val layout: Int
                    ) : RecyclerView.Adapter<RecursosAdapter.ViewHolder>() {

    private var dataList: List<Recurso> = emptyList()

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

    internal fun setRecursos(recursos: List<Recurso>) {
        this.dataList = recursos
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Recurso){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvNombrePajaro = itemView.findViewById(R.id.tvrowNombrePajaro) as TextView
            val ivPajaro = itemView.findViewById(R.id.ivPajaro) as ImageView

            tvNombrePajaro.text = dataItem.titulo

            // foto de internet a traves de Picasso
            Picasso.get().load("${dataItem.url}").into(ivPajaro)

            itemView.tag = dataItem

        }

    }
}