package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DesejoAdapter (val lista: MutableList<Desejo>) : RecyclerView.Adapter<DesejoAdapter.DesejoViewHolder>()
{
    var listener: OnItemClickRecycleView? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesejoAdapter.DesejoViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item, parent, false)
        return DesejoViewHolder(view)
    }


    override fun onBindViewHolder(holder: DesejoViewHolder, position: Int) {
        val desejo: Desejo = this.lista.get(position)

        holder.nome.text = desejo.descricao
    }


    override fun getItemCount(): Int { return this.lista.size }


    fun add(desejo: Desejo) { this.lista.add(desejo) }


    fun del(position: Int){
        this.lista.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class DesejoViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        var nome: TextView

        init {
            this.nome = itemView.findViewById(R.id.ItemNome)

            itemView.setOnClickListener{

                this@DesejoAdapter.listener?.onItemClick(this.adapterPosition)

            }

            itemView.setOnLongClickListener{

                return@setOnLongClickListener true

            }
        }
    }
}