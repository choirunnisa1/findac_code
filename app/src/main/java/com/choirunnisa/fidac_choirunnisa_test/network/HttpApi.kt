package com.choirunnisa.fidac_choirunnisa_test.network

import com.choirunnisa.fidac_choirunnisa_test.model.DetailProductsData
import com.choirunnisa.fidac_choirunnisa_test.model.ProductsData
import com.choirunnisa.fidac_choirunnisa_test.model.UsersData
import com.choirunnisa.fidac_choirunnisa_test.model.reqProduct
import retrofit2.Call
import retrofit2.http.*
import java.net.CacheRequest

interface FindacApi {
    @GET("v1/api/technical_test/get_product")
    fun getProducts() : Call<ProductsData>

    @POST("v1/api/technical_test/get_product_id")
    fun getDetailProduct(
        @Body request: reqProduct
    ) : Call<DetailProductsData>

    @GET("v1/api/technical_test/get_mutable_title")
    fun getUsers() : Call<UsersData>
}