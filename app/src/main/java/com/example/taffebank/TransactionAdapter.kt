package com.example.taffebank

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(
    private val listaTransaction: MutableList<Transaction>,
    private var isLoading: Boolean = true
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_TRANSACTION = 1
    private val VIEW_TYPE_SKELETON = 2

    // Classe para transações reais
    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val valorpgt: TextView = itemView.findViewById(R.id.valorpgt)
        val descricaoopgt: TextView = itemView.findViewById(R.id.descricaoopgt)
        val metodopgt: TextView = itemView.findViewById(R.id.metodopgt)
        val itemLayout: View = itemView.findViewById(R.id.transaction_item_layout)
    }

    class SkeletonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_TRANSACTION) {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.historico, parent, false)
            TransactionViewHolder(itemView)
        } else {
            val skeletonView = LayoutInflater.from(parent.context).inflate(R.layout.skeleton_item, parent, false)
            SkeletonViewHolder(skeletonView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TransactionViewHolder) {
            val transaction = listaTransaction[listaTransaction.size - 1 - position]
            holder.valorpgt.text = "R$ %.2f".format(transaction.valor)
            holder.descricaoopgt.text = transaction.descricao
            holder.metodopgt.text = transaction.type

            if (transaction.valor >= 0) {
                holder.itemLayout.setBackgroundColor(Color.parseColor("#33691E"))
            } else {
                holder.itemLayout.setBackgroundColor(Color.parseColor("#B71C1C"))
            }
        }
    }

    override fun getItemCount(): Int {
        return if (isLoading) {
            // Exibe skeleton quando está carregando
            5 // Defina quantos skeletons exibir
        } else {
            listaTransaction.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading) VIEW_TYPE_SKELETON else VIEW_TYPE_TRANSACTION
    }

    // Método para alternar o estado de carregamento
    fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }
    fun isLoading(): Boolean {
        return isLoading
    }
}

