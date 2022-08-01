package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowDetailPersonBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Name
import com.squareup.picasso.Picasso

class PersonAdapter(val context: Context) : RecyclerView.Adapter<PersonAdapter.ListViewHolder>(){

    var listName = mutableListOf<Name>()

    fun addData(list: List<Name>){
        listName.clear()
        this.listName = list.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = RowDetailPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       with(holder){
           with(listName[position]){
               binding.judul.text = this.subtitle1
               binding.isian.text = this.value1
           }
       }
    }

    override fun getItemCount(): Int = listName.size

    inner class ListViewHolder(val binding: RowDetailPersonBinding) : RecyclerView.ViewHolder(binding.root)

    }


