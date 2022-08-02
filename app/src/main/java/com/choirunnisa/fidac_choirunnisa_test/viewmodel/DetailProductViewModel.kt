package com.choirunnisa.fidac_choirunnisa_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.choirunnisa.fidac_choirunnisa_test.interactor.FindacInteractor
import com.choirunnisa.fidac_choirunnisa_test.model.*
import com.choirunnisa.fidac_choirunnisa_test.network.FindacService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailProductViewModel @Inject constructor(findacService: FindacService): ViewModel(){

    private val findacInteractor = FindacInteractor(findacService)

    val title = MutableLiveData<String>()
    val harga = MutableLiveData<String>()
    val desc = MutableLiveData<String>()
    val detail = MutableLiveData<DetailProduct>()
    val image = MutableLiveData<List<Variants>>()


    suspend fun getTitle(id: Int){
        val result = GlobalScope.async(Dispatchers.IO){
            findacInteractor.getDetailProduct(id)
        }.await()
        GlobalScope.launch(Dispatchers.Main){
            detail.value = result
//            title.value = result.product_name
//            desc.value = result.product_desc
//            harga.value = result.product_price
            image.value = result.variant
        }
    }





}