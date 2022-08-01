package com.choirunnisa.fidac_choirunnisa_test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.adapter.ProductsAdapter
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Products
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.ListProductViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ListProductActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vm: ListProductViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ListProductViewModel::class.java)
    }

    private var _binding : ActivityListProductBinding? = null
    private val binding get() =  _binding!!

    lateinit var rvProductsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        job = Job()
        _binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvProductsAdapter = ProductsAdapter(this)
        binding.rvListProduct.apply {
            adapter = rvProductsAdapter
            layoutManager = LinearLayoutManager(this@ListProductActivity)
        }

        launch {
            try {
                vm.getListProducts()
            }catch (e:Exception){
                Toast.makeText(this@ListProductActivity, "gagal load data", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tbListProduct.setNavigationIcon(R.drawable.ic_arrow_back_black)
        binding.tbListProduct.setNavigationOnClickListener {
            onBackPressed()
        }

        vm.listProducts.observe(this, Observer(::onLoadProducts))

        binding.goToPerson.setOnClickListener {
            startActivity(Intent(this, DetailUserActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        launch {
            vm.getListProducts()
        }
    }

    fun goToDetail(id: Int){
        startActivity(Intent(this,DetailProductActivity::class.java).apply {
            putExtra(DetailProductActivity.paramIdProduct, id)
        })
    }
    private fun onLoadProducts(product: List<Products>?){
        product ?: return
        rvProductsAdapter.addData(product)
        rvProductsAdapter.notifyDataSetChanged()
    }
}