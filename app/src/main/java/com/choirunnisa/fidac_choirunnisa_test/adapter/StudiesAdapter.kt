package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowDetailPerson2Binding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowDetailPersonBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Pendidikan
import com.squareup.picasso.Picasso

class StudiesAdapter(val context: Context) : RecyclerView.Adapter<StudiesAdapter.ListViewHolder>(){

    var listPendidikan = mutableListOf<Pendidikan>()

    fun addData(list: List<Pendidikan>){
        listPendidikan.clear()
        this.listPendidikan = list.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = RowDetailPerson2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       with(holder){
           with(listPendidikan[position]){
               binding.judul.text = this.subtitle2
               binding.isian.text = this.value2
           }
       }
    }

    override fun getItemCount(): Int = listPendidikan.size

    inner class ListViewHolder(val binding: RowDetailPerson2Binding) : RecyclerView.ViewHolder(binding.root)

    }


