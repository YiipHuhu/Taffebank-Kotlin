package com.example.taffebank

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(
    private val listaTransaction: MutableList<Transaction>) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val valorpgt: TextView = itemView.findViewById(R.id.valorpgt)
        val descricaoopgt: TextView = itemView.findViewById(R.id.descricaoopgt)
        val metodopgt: TextView = itemView.findViewById(R.id.metodopgt)
        val itemLayout: View = itemView.findViewById(R.id.transaction_item_layout) // O layout da transação
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.historico, parent, false)
        return TransactionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = listaTransaction[position]


        holder.valorpgt.text = "R$ %.2f".format(transaction.valor)
        holder.descricaoopgt.text = transaction.descricao
        holder.metodopgt.text = transaction.type


        if (transaction.valor >= 0) {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#79BD9A"))
        } else {
            holder.itemLayout.setBackgroundColor(Color.parseColor("#BD798C"))
        }
    }

    override fun getItemCount() = listaTransaction.size
}