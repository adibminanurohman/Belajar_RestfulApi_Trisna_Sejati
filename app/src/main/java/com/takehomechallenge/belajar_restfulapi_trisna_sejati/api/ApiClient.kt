package com.takehomechallenge.belajar_restfulapi_trisna_sejati.api

import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.services.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiClient {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val prductService: ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }
}