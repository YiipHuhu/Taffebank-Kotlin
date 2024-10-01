
package com.example.taffebank

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var saldoAtual: Double = 0.0
    private lateinit var saldoTextView: TextView
    private lateinit var valorInsert: EditText
    private lateinit var descricaoInsert: EditText
    private lateinit var transactionAdapter: TransactionAdapter
    private val listaTransaction = mutableListOf<Transaction>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saldoTextView = findViewById(R.id.saldo)
        valorInsert = findViewById(R.id.valorInsert)
        descricaoInsert = findViewById(R.id.descricao)

        val btnCredito: Button = findViewById(R.id.btncredito)
        val btnDebito: Button = findViewById(R.id.btndebito)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewAlunos)

        transactionAdapter = TransactionAdapter(listaTransaction)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = transactionAdapter


        btnCredito.setOnClickListener {
            val valor = valorInsert.text.toString().toDoubleOrNull()
            val descricao = descricaoInsert.text.toString()
            if (valor != null && descricao.isNotBlank()) {
                adicionarTransacao(valor, descricao, "Crédito")
            }
        }

        btnDebito.setOnClickListener {
            val valor = valorInsert.text.toString().toDoubleOrNull()
            val descricao = descricaoInsert.text.toString()
            if (valor != null && descricao.isNotBlank()) {
                adicionarTransacao(-valor, descricao, "Débito")
            }
        }
    }

    private fun adicionarTransacao(valor: Double, descricao: String, tipo: String) {
        saldoAtual += valor
        listaTransaction.add(Transaction(valor, descricao, tipo))
        transactionAdapter.notifyDataSetChanged()
        atualizarSaldo()
    }

    private fun atualizarSaldo() {
        saldoTextView.text = "R$ %.2f".format(saldoAtual)
    }
}
