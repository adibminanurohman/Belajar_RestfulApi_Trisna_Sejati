package com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.services

import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getAllProduct(): Call<ProductResponse>
}