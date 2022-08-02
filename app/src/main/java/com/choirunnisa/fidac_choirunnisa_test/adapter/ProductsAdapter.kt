package com.choirunnisa.fidac_choirunnisa_test.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.RowListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Products
import com.choirunnisa.fidac_choirunnisa_test.ui.ListProductActivity
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class ProductsAdapter(val context: Context) : BaseAdapter() {

    var listProducts = mutableListOf<Products>()
    private var layoutInflater: LayoutInflater? = null

    fun addData(list: List<Products>) {
        listProducts.clear()
        this.listProducts = list.toMutableList()
        notifyDataSetChanged()
    }


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view = RowListProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ListViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//       with(holder){
//           with(listProducts[position]){
//               binding.productName.text = this.product_name
//               val photoUrl = "https://app.minjem.biz.id/upload/tech_test_image/${this.product_image}"
//               Picasso.get().load(photoUrl)
//                   .placeholder(R.drawable.avatar)
//                   .resize(120, 200)
//                   .error(R.drawable.avatar).into(binding.imgProduct)
//               binding.productPrice.text = this.product_price
//               binding.to.setOnClickListener {
//                   (context as ListProductActivity).goToDetail(this.id_produk)
//               }
//
//           }
//       }
//    }
//
//    override fun getItemCount(): Int = listProducts.size
//
//    inner class ListViewHolder(val binding: RowListProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getCount(): Int {
        return listProducts.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {
        var convertView = p1
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.row_list_product, null)
        }

        val image = convertView!!.findViewById<ImageView>(R.id.img_product)
        val nameProduk = convertView!!.findViewById<TextView>(R.id.product_name)
        val price = convertView!!.findViewById<TextView>(R.id.product_price)
        val card = convertView!!.findViewById<LinearLayout>(R.id.to)

        price.text = NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(listProducts.get(i).product_price.toDouble())

        val photoUrl =
            "https://app.minjem.biz.id/upload/tech_test_image/${listProducts.get(i).product_image}"
        Picasso.get().load(photoUrl)
            .placeholder(R.drawable.avatar)
            .resize(120, 200)
            .error(R.drawable.avatar).into(image)
        nameProduk.text = listProducts.get(i).product_name
        card.setOnClickListener {
            (context as ListProductActivity).goToDetail(listProducts.get(i).id_produk)

        }
            return convertView
        }
}





