package com.choirunnisa.fidac_choirunnisa_test.viewmodel

import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.choirunnisa.fidac_choirunnisa_test.interactor.FindacInteractor
import com.choirunnisa.fidac_choirunnisa_test.model.Products
import com.choirunnisa.fidac_choirunnisa_test.network.FindacService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListProductViewModel @Inject constructor(findacService: FindacService):ViewModel() {
    private val findacInteractor = FindacInteractor(findacService)

    val listProducts = MutableLiveData<List<Products>>()

    suspend fun getListProducts(){
        val result = GlobalScope.async(Dispatchers.IO){
            findacInteractor.getListProduct()
        }.await()
        GlobalScope.launch(Dispatchers.Main){
            listProducts.value = result
        }
    }


}