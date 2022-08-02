package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowDetailPersonBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowVariantBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Variants
import com.choirunnisa.fidac_choirunnisa_test.ui.DetailProductActivity

class PopUpAdapter(val context: Context) : RecyclerView.Adapter<PopUpAdapter.ListViewHolder>(){

    var listVariant = mutableListOf<Variants>()

    fun addData(list: List<Variants>){
        listVariant.clear()
        this.listVariant = list.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = RowVariantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       with(holder){
           with(listVariant[position]){
               binding.color.setBackgroundColor(Color.parseColor(this.variant_color))
               binding.color.setOnClickListener {
                   (context as DetailProductActivity).changeImage(this.variant_image)
               }
           }
       }
    }

    override fun getItemCount(): Int = listVariant.size

    inner class ListViewHolder(val binding: RowVariantBinding) : RecyclerView.ViewHolder(binding.root)

    }


