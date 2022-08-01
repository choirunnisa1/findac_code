package com.choirunnisa.fidac_choirunnisa_test.interactor

import com.choirunnisa.fidac_choirunnisa_test.model.DetailProduct
import com.choirunnisa.fidac_choirunnisa_test.model.Products
import com.choirunnisa.fidac_choirunnisa_test.model.User
import com.choirunnisa.fidac_choirunnisa_test.network.FindacService

class FindacInteractor(val findacService: FindacService) {

    suspend fun getListProduct() : List<Products>{
        val result = findacService.getProducts().data
        return result
    }

    suspend fun getUsers() : User{
        val result = findacService.getUsers().data[0]
        return result
    }

    suspend fun getDetailProduct(id:Int): DetailProduct{
        val response = findacService.getDetailProduct(id)
        val data = response.data[0]
        return data
    }
}