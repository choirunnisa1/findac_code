package com.choirunnisa.fidac_choirunnisa_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.choirunnisa.fidac_choirunnisa_test.databinding.ActivityListProductBinding
import com.choirunnisa.fidac_choirunnisa_test.viewmodel.ListProductViewModel
import dagger.android.AndroidInjection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListProductBinding.inflate(layoutInflater)
        AndroidInjection.inject(this)

        job = Job()
        setContentView(binding.root)
    }
}