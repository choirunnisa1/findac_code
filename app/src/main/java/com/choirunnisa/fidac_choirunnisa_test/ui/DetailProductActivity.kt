package com.choirunnisa.fidac_choirunnisa_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.choirunnisa.fidac_choirunnisa_test.R
import com.choirunnisa.fidac_choirunnisa_test.adapter.PersonAdapter
import com.choirunnisa.fidac_choirunnisa_test.adapter.StudiesAdapter
import com.choirunnisa.fidac_choirunnisa_test.adapter.VariantAdapter
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityDetailProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Variants
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.DetailPersonViewModel
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.DetailProductViewModel
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.ListProductViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DetailProductActivity : AppCompatActivity(), CoroutineScope{

    companion object{
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

    private var _binding : ActivityDetailProductBinding? = null
    private val binding get() =  _binding!!

    var idProduct = 0

    lateinit var variantAdapter: VariantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        job = Job()
        _binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        idProduct = intent.getIntExtra(paramIdProduct, 0)

        launch {
            try {
                vm.getTitle(idProduct)
            }catch (e:Exception){
                Toast.makeText(this@DetailProductActivity, "gagal load data $e", Toast.LENGTH_SHORT).show()
            }
        }

        vm.title.observe(this, Observer(::onTitle))
        vm.desc.observe(this, Observer(::onDesc))
        vm.harga.observe(this, Observer(::onPrice))
        vm.image.observe(this, Observer(::onVarian))

        variantAdapter = VariantAdapter(this)
        binding.varian.apply {
            adapter = variantAdapter
            layoutManager = LinearLayoutManager(this@DetailProductActivity)
        }

        binding.tbNameProduct.setNavigationIcon(R.drawable.ic_arrow_back_black)
        binding.tbNameProduct.setNavigationOnClickListener {
            onBackPressed()
        }

    }

    override fun onResume() {
        super.onResume()
        launch {
            vm.getTitle(idProduct)
        }
    }

    private fun onTitle(result: String?){
        result ?: return
        binding.tbNameProduct.title = result
    }

    private fun onDesc(result: String?){
        result ?: return
        binding.desc.text = result
    }

    private fun onPrice(result: String?){
        result ?: return
        binding.price.text = result
    }

    private fun onVarian(result: List<Variants>?){
        result ?: return
        variantAdapter.addData(result)
        variantAdapter.notifyDataSetChanged()
    }
}