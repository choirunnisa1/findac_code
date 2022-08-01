package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Products

class ProductsAdapter(val context: Context) : RecyclerView.Adapter<ProductsAdapter.ListViewHolder>(){

    var listProducts = mutableListOf<Products>()

    fun addData(list: List<Products>){
        listProducts.clear()
        this.listProducts = list.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ActivityListProductBinding.inflate(LayoutInflater).from(parent.context)
            .inflate(R.layout.row_list_product, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = listProducts.size

    inner class ListViewHolder(private val binding: ActivityListProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(listProduct : Products){

        }
    }


}