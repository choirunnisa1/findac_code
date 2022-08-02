package com.choirunnisa.fidac_choirunnisa_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.adapter.*
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityDetailProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.PopUpButtomBinding
import com.choirunnisa.fidac_choirunnisa_test.model.DetailProduct
import com.choirunnisa.fidac_choirunnisa_test.model.Variants
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.DetailPersonViewModel
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.DetailProductViewModel
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.ListProductViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DetailProductActivity : AppCompatActivity(), CoroutineScope {

    companion object {
        const val paramIdProduct = "param_id_product"
    }

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vm: DetailProductViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DetailProductViewModel::class.java)
    }

    private var _binding: ActivityDetailProductBinding? = null
    private val binding get() = _binding!!

    var idProduct = 0

    lateinit var variantAdapter: VariantAdapter
    lateinit var imageAdapter: ImageAdapter
    lateinit var popUpAdapter: PopUpAdapter


    var imageDetail = ""
    lateinit var imgPopUp : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        job = Job()
        _binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idProduct = intent.getIntExtra(paramIdProduct, 0)

        imageAdapter = ImageAdapter(this)
        launch {
            try {
                vm.getTitle(idProduct)
            } catch (e: Exception) {
                Toast.makeText(this@DetailProductActivity, "gagal load data $e", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        vm.detail.observe(this, Observer(::onDetail))


        binding.slider.adapter = imageAdapter
        binding.slider.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        vm.image.observe(this, Observer(::loadImage))

        variantAdapter = VariantAdapter(this)
        binding.varian.apply {
            adapter = variantAdapter
            layoutManager = LinearLayoutManager(
                this@DetailProductActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        popUpAdapter = PopUpAdapter(this)

        binding.tbNameProduct.setNavigationIcon(R.drawable.ic_arrow_back_black)
        binding.tbNameProduct.setNavigationOnClickListener {
            onBackPressed()
        }

        TabLayoutMediator(binding.indicatorContent, binding.slider) { tab, position ->
            //Some implementation
        }.attach()



    }

    override fun onResume() {
        super.onResume()
        launch {
            vm.getTitle(idProduct)
        }
    }



    private fun onDetail(result: DetailProduct?) {
        result ?: return
        imageDetail = result.product_image
        binding.tbNameProduct.title = result.product_name
        binding.desc.text = result.product_desc
        binding.price.text =
            NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(result.product_price.toDouble())
        variantAdapter.addData(result.variant)
        variantAdapter.notifyDataSetChanged()
    }


    private fun onLoadVarian(result: DetailProduct?){
        result ?: return
        popUpAdapter.addData(result.variant)
        popUpAdapter.notifyDataSetChanged()
    }

    private fun loadImage(result: List<Variants>?) {
        result ?: return
        imageAdapter.addData(result)
        imageAdapter.notifyDataSetChanged()
        binding.slider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("position", "$position")
                super.onPageSelected(position)

            }
        })

        (binding.slider.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_ALWAYS


    }

    fun showPopUp(){

        val dialog = BottomSheetDialog(this)

        dialog.setContentView(R.layout.pop_up_buttom)
        imgPopUp = dialog.findViewById(R.id.img_variant)!!
        val rv = dialog.findViewById<RecyclerView>(R.id.rv_colors)
        val btn = dialog.findViewById<Button>(R.id.btn_ok)
        vm.detail.observe(this, Observer(::onLoadVarian))

        val photoUrl = "https://app.minjem.biz.id/upload/tech_test_image/$imageDetail"
        Picasso.get().load(photoUrl)
            .placeholder(R.drawable.avatar)
            .resize(120, 200)
            .error(R.drawable.avatar).into(imgPopUp)

        btn?.setOnClickListener {
            Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }


        rv?.apply {
            adapter = popUpAdapter
            layoutManager = LinearLayoutManager(
                this@DetailProductActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        dialog.show()

    }

    fun changeImage(imgSrc: String){
        val photoUrl = "https://app.minjem.biz.id/upload/tech_test_image/$imgSrc"
        Picasso.get().load(photoUrl)
            .placeholder(R.drawable.avatar)
            .resize(120, 200)
            .error(R.drawable.avatar).into(imgPopUp)

    }



}