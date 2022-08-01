package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowDetailPersonBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowImageBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowVariantBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Variants
import com.squareup.picasso.Picasso

class ImageAdapter(val context: Context) : RecyclerView.Adapter<ImageAdapter.ListViewHolder>(){

    var listVariant = mutableListOf<Variants>()

    fun addData(list: List<Variants>){
        listVariant.clear()
        this.listVariant = list.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = RowImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       with(holder){
           with(listVariant[position]){
               val photoUrl = "https://app.minjem.biz.id/upload/tech_test_image/${this.variant_image}"
               Picasso.get().load(photoUrl)
                   .placeholder(R.drawable.avatar)
                   .resize(120, 200)
                   .error(R.drawable.avatar).into(binding.img)
           }
       }
    }

    override fun getItemCount(): Int = listVariant.size

    inner class ListViewHolder(val binding: RowImageBinding) : RecyclerView.ViewHolder(binding.root)

    }


