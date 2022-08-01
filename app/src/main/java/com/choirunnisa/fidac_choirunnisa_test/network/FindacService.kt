package com.choirunnisa.fidac_choirunnisa_test.network

import com.choirunnisa.fidac_choirunnisa_test.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface FindacService {
    suspend fun getProducts():ProductsData
    suspend fun getDetailProduct(id: Int): DetailProductsData
    suspend fun getUsers(): UsersData
}

class HttpFindacService(val findacApi: FindacApi): FindacService{
    override suspend fun getProducts(): ProductsData = suspendCoroutine { continuation ->
        findacApi.getProducts().enqueue(object : Callback<ProductsData>{
            override fun onResponse(call: Call<ProductsData>, response: Response<ProductsData>) {
                if (response.isSuccessful){
                    val result = response.body()
                    if (result == null){
                        continuation.resumeWithException(Exception("no result data"))
                    } else continuation.resume(result)
                } else {
                    val error = if (response.code() == 404) Exception("Not Found") else Exception("failed to get data, response code ${response.code()}")
                    continuation.resumeWithException(error)
                }
            }

            override fun onFailure(call: Call<ProductsData>, t: Throwable) {
                continuation.resumeWithException(t)
            }

        })
    }

    override suspend fun getDetailProduct(id: Int): DetailProductsData = suspendCoroutine { continuation ->
        findacApi.getDetailProduct(reqProduct(id)).enqueue(object : Callback<DetailProductsData>{
            override fun onResponse(
                call: Call<DetailProductsData>,
                response: Response<DetailProductsData>
            ) {
                if (response.isSuccessful){
                    val result = response.body()
                    if (result == null){
                        continuation.resumeWithException(Exception("no result data"))
                    } else continuation.resume(result)
                } else {
                    val error = if (response.code() == 404) Exception("Not Found") else Exception("failed to get data, response code ${response.code()}")
                    continuation.resumeWithException(error)
                }
            }

            override fun onFailure(call: Call<DetailProductsData>, t: Throwable) {
                continuation.resumeWithException(t)
            }

        })
    }

            override suspend fun getUsers(): UsersData = suspendCoroutine { continuation ->
                findacApi.getUsers().enqueue(object : Callback<UsersData>{
                    override fun onResponse(call: Call<UsersData>, response: Response<UsersData>) {
                        if (response.isSuccessful){
                            val result = response.body()
                            if (result == null){
                                continuation.resumeWithException(Exception("no result data"))
                            } else continuation.resume(result)
                        } else {
                            val error = if (response.code() == 404) Exception("Not Found") else Exception("failed to get data, response code ${response.code()}")
                            continuation.resumeWithException(error)
                        }
                    }

                    override fun onFailure(call: Call<UsersData>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }

                })
            }

}