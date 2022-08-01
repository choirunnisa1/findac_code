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
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityDetailProductBinding
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityDetailUserBinding
import com.choirunnisa.fidac_choirunnisa_test.model.Name
import com.choirunnisa.fidac_choirunnisa_test.model.Pendidikan
import com.choirunnisa.fidac_choirunnisa_test.model.Products
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.DetailPersonViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DetailUserActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vm: DetailPersonViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DetailPersonViewModel::class.java)
    }

    private var _binding : ActivityDetailUserBinding? = null
    private val binding get() =  _binding!!

    lateinit var personAdapter: PersonAdapter
    lateinit var studiesAdapter: StudiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        job = Job()
        _binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personAdapter = PersonAdapter(this)
        binding.rvDataDiri.apply {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(this@DetailUserActivity)
        }

        studiesAdapter = StudiesAdapter(this)
        binding.rvRiwayatPendidikan.apply {
            adapter = studiesAdapter
            layoutManager = LinearLayoutManager(this@DetailUserActivity)
        }

        launch {
            try {
                vm.getTitle1()
                vm.getTitle2()
                vm.getListDataDiri()
                vm.getListStudies()
            } catch (e:Exception){
                Toast.makeText(this@DetailUserActivity, "gagal load data", Toast.LENGTH_SHORT).show()
            }
        }

        vm.title1.observe(this, Observer(::onLoadTitle1))
        vm.title2.observe(this, Observer(::onLoadTitle2))
        vm.dataDiri.observe(this, Observer(::onLoadDataDiri))
        vm.studies.observe(this, Observer(::onLoadDataPendidikan))

        binding.tbPerson.setNavigationIcon(R.drawable.ic_arrow_back_black)
        binding.tbPerson.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        launch {
            vm.getTitle1()
            vm.getTitle2()
            vm.getListDataDiri()
            vm.getListStudies()
        }
    }

    private fun onLoadDataDiri(result: List<Name>?){
        result ?: return
        personAdapter.addData(result)
        personAdapter.notifyDataSetChanged()
    }

    private fun onLoadDataPendidikan(result: List<Pendidikan>?){
        result ?: return
        studiesAdapter.addData(result)
        studiesAdapter.notifyDataSetChanged()
    }

    private fun onLoadTitle1(result: String?){
        result ?: return
        binding.title1.text = result
    }

    private fun onLoadTitle2(result: String?){
        result ?: return
        binding.title2.text = result
    }
}