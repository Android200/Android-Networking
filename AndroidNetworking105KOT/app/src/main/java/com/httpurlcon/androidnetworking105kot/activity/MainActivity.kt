package com.httpurlcon.androidnetworking105kot.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Network
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.httpurlcon.androidnetworking105kot.R
import com.httpurlcon.androidnetworking105kot.adapter.MyAdapter
import com.httpurlcon.androidnetworking105kot.model.Item
import com.httpurlcon.androidnetworking105kot.model.ItemResponse
import com.httpurlcon.androidnetworking105kot.rest.APIClient
import com.httpurlcon.androidnetworking105kot.rest.APIinterface
import com.httpurlcon.androidnetworking105kot.util.ListItemDecorations
import com.httpurlcon.androidnetworking105kot.util.NetworkConnected
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    lateinit var myCustomAdapter: MyAdapter

    lateinit var  mHandler: Handler
    lateinit var  mRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler = Handler()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)

        Retry.setOnClickListener {
            if (NetworkConnected.isNetworkConnected(applicationContext)){
                loadJson()
            }else{
                val snackbar: Snackbar = Snackbar.make(constraint,"No Internet Connection, Try Again!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY") {
                            loadJson()
                        }
                snackbar.setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                val sbView: View = snackbar.view
                val textView: TextView = sbView.findViewById(android.support.design.R.id.snackbar_text)
                textView.setTextColor(Color.YELLOW)
                snackbar.show()
            }

        }

        if(NetworkConnected.isNetworkConnected(applicationContext)){
            loadJson()
        }else{
            Retry.visibility = View.VISIBLE
            textError.visibility = View.VISIBLE

            val snackbar: Snackbar = Snackbar.make(constraint,"No Internet Connection, Try Again!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY") {
                        loadJson()
                    }
            snackbar.setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            val sbView: View = snackbar.view
            val textView: TextView = sbView.findViewById(android.support.design.R.id.snackbar_text)
            textView.setTextColor(Color.YELLOW)
            snackbar.show()
        }

        swipeRefresh.setColorSchemeResources(
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_red_light)

        swipeRefresh.setOnRefreshListener {
            if(NetworkConnected.isNetworkConnected(applicationContext)){
                mRunnable = Runnable {
                    loadJson()
                    val snackbar: Snackbar = Snackbar.make(constraint,"Refreshed!", Snackbar.LENGTH_LONG)
                            .setAction("") {
                                loadJson()
                            }
                    snackbar.setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                    val sbView: View = snackbar.view
                    val textView: TextView = sbView.findViewById(android.support.design.R.id.snackbar_text)
                    textView.setTextColor(Color.YELLOW)
                    snackbar.show()

                    swipeRefresh.isRefreshing = false
                }

                mHandler.postDelayed(
                        mRunnable,1000
                )

            }else{
                Retry.visibility = View.VISIBLE
                textError.visibility = View.VISIBLE
                val snackbar: Snackbar = Snackbar.make(constraint,"No Internet Connection, Try Again!", Snackbar.LENGTH_LONG)
                        .setAction("RETRY") {
                            loadJson()
                        }
                snackbar.setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                val sbView: View = snackbar.view
                val textView: TextView = sbView.findViewById(android.support.design.R.id.snackbar_text)
                textView.setTextColor(Color.YELLOW)
                snackbar.show()
            }
        }



    }
    fun loadJson() {


        progressBar.visibility = View.VISIBLE

        var apiServies = APIClient.client.create(APIinterface::class.java)

        val call = apiServies.getDevsKano()

        call.enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                if (response.isSuccessful) {
                    var listofDevs: List<Item> = response.body()!!.items
                    myCustomAdapter = MyAdapter(applicationContext, listofDevs)
                    recyclerView.adapter = myCustomAdapter
                    progressBar.visibility = View.GONE
                    Retry.visibility = View.GONE
                    textError.visibility = View.GONE
                } else {
                    Log.e(TAG, response.toString())
                }

            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Retry.visibility = View.VISIBLE
                textError.visibility = View.VISIBLE
                Log.e(TAG, t.toString())
            }
        })

    }

}
