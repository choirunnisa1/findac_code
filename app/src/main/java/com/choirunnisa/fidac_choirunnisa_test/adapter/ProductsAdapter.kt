package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Products
import com.choirunnisa.fidac_choirunnisa_test.ui.ListProductActivity
import com.squareup.picasso.Picasso

class ProductsAdapter(val context: Context) : RecyclerView.Adapter<ProductsAdapter.ListViewHolder>(){

    var listProducts = mutableListOf<Products>()

    fun addData(list: List<Products>){
        listProducts.clear()
        this.listProducts = list.toMutableList()
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = RowListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       with(holder){
           with(listProducts[position]){
               binding.productName.text = this.product_name
               val photoUrl = "https://app.minjem.biz.id/upload/tech_test_image/${this.product_image}"
               Picasso.get().load(photoUrl)
                   .placeholder(R.drawable.avatar)
                   .resize(120, 200)
                   .error(R.drawable.avatar).into(binding.imgProduct)
               binding.productPrice.text = this.product_price
               binding.to.setOnClickListener {
                   (context as ListProductActivity).goToDetail(this.id_produk)
               }

           }
       }
    }

    override fun getItemCount(): Int = listProducts.size

    inner class ListViewHolder(val binding: RowListProductBinding) : RecyclerView.ViewHolder(binding.root)

    }


