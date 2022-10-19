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
import net.azarquiel.model.Comentario


/**
 * Created by pacopulido on 9/10/18.
 */
class ComentariosAdapter(val context: Context,
                         val layout: Int
                    ) : RecyclerView.Adapter<ComentariosAdapter.ViewHolder>() {

    private var dataList: List<Comentario> = emptyList()

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

    internal fun setComentarios(comentarios: List<Comentario>) {
        this.dataList = comentarios
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Comentario){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val tvFecha = itemView.findViewById(R.id.tvFecha) as TextView
            val tvContenidoComentario = itemView.findViewById(R.id.tvContenidoComentario) as TextView
            val tvNick = itemView.findViewById(R.id.tvNick) as TextView

            tvFecha .text= dataItem.fecha
            tvContenidoComentario.text = dataItem.comentario
            tvNick.text = dataItem.nick

            itemView.tag = dataItem

        }

    }
}