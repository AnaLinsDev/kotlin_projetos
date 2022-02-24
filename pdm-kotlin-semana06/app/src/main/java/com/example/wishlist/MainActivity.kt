package com.example.wishlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var lvDesejos: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var lista: MutableList<Desejo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.lista = mutableListOf()

        this.lvDesejos = findViewById<View>(R.id.lvDesejos) as RecyclerView
        this.fabAdd = findViewById(R.id.fabAdd)

        this.lvDesejos.adapter = DesejoAdapter(this.lista)
        (this.lvDesejos.adapter as DesejoAdapter).listener = OnItemClickListener()


        val resultForm = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val desejo = it.data?.getSerializableExtra("DESEJO") as Desejo


                this.lista.add(Desejo (desejo.descricao, desejo.nota))
                (this@MainActivity.lvDesejos.adapter as DesejoAdapter).notifyDataSetChanged()
            }
        }

        this.fabAdd.setOnClickListener{
            val intent = Intent(this, FormActivity::class.java)
            resultForm.launch(intent)
        }
    }



    inner class OnItemClickListener: OnItemClickRecycleView{
        override fun onItemClick(position: Int) {
            val desejo = this@MainActivity.lista.get(position)
            Toast.makeText(this@MainActivity, desejo.descricao, Toast.LENGTH_SHORT).show()
        }
    }

    inner class OnItemLongClickListener: AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long

        ): Boolean {

            val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity )

            builder.setTitle("ATENÇÃO !!!")
            builder.setMessage("Deseja excluir o "+lista[position]+" ? ");

            builder.setPositiveButton("SIM"){dialog, which ->

                (this@MainActivity.lvDesejos.adapter as DesejoAdapter).del(position)

                }

            builder.setNegativeButton("NAO"){dialog, which ->

                dialog.dismiss()

                }

            builder.show()

            return true
        }
    }
}

