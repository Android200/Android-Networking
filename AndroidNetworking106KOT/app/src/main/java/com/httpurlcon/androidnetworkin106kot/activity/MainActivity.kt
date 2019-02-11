package com.httpurlcon.androidnetworkin106kot.activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import com.httpurlcon.androidnetworkin106kot.R
import com.httpurlcon.androidnetworkin106kot.adapter.TennisAdapter
import com.httpurlcon.androidnetworkin106kot.model.Tennis
import com.httpurlcon.androidnetworkin106kot.model.TennisResponse
import com.httpurlcon.androidnetworkin106kot.rest.APIClient
import com.httpurlcon.androidnetworkin106kot.rest.APIinterface
import com.httpurlcon.androidnetworkin106kot.util.ListItemDecorations
import com.httpurlcon.androidnetworkin106kot.util.NetworkConnected
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.simpleName

    lateinit var tennisCustomAdapter: TennisAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)

        progressBar.visibility = View.VISIBLE

        var apiServices = APIClient.client.create(APIinterface::class.java)

        val call = apiServices.getTennisPlayers()

        call.enqueue(object : Callback<TennisResponse>{

            override fun onResponse(call: Call<TennisResponse>, response: Response<TennisResponse>) {
                if (response.isSuccessful){
                    var listofPlayers: List<Tennis> = response.body()!!.data
                    tennisCustomAdapter = TennisAdapter(applicationContext, listofPlayers)
                    recyclerView.adapter = tennisCustomAdapter
                    progressBar.visibility = View.GONE

                }else{
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<TennisResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e(TAG, t.toString())
                toast("Error ${t.toString()}")
            }
        })
    }

    fun loadJson(){

    }
}
