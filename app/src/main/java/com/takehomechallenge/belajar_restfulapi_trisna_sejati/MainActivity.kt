package com.takehomechallenge.belajar_restfulapi_trisna_sejati

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.ApiClient
import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.adapter.ProductAdapter
import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.model.ProductResponse
import com.takehomechallenge.belajar_restfulapi_trisna_sejati.api.model.ProductsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ProductResponse>
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshLayout = findViewById(R.id.refreshLayout)
        recyclerView = findViewById(R.id.recyclerView)
        productAdapter = ProductAdapter { productsItem ->  }
        recyclerView.adapter = productAdapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
    }

 private fun productOnClick(productsItem: ProductsItem){
     Toast.makeText(applicationContext, productsItem.description, Toast.LENGTH_SHORT).show()
 }
    private fun getData(){
        swipeRefreshLayout.isRefreshing = true

        call = ApiClient.prductService.getAllProduct()
        call.enqueue(object : Callback<ProductResponse>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful){
                    productAdapter.submitList(response.body()?.products)
                    productAdapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }
}