package com.httpurlcon.androidnetworking108kot.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.httpurlcon.androidnetworking108kot.R
import com.httpurlcon.androidnetworking108kot.adapter.PhonebookAdapter
import com.httpurlcon.androidnetworking108kot.model.Payload
import com.httpurlcon.androidnetworking108kot.model.Phonebook
import com.httpurlcon.androidnetworking108kot.rest.APIClient
import com.httpurlcon.androidnetworking108kot.rest.APIinterface
import com.httpurlcon.androidnetworking108kot.util.ListItemDecorations
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.simpleName

    lateinit var phoneBookCustomAdapter: PhonebookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)

        progressBar.visibility = View.VISIBLE

        var apiServices = APIClient.client.create(APIinterface::class.java)

        val call = apiServices.getPhonebook()

        call.enqueue(object : Callback<Phonebook>{

            override fun onResponse(call: Call<Phonebook>, response: Response<Phonebook>) {
               if(response.isSuccessful){
                   var listOfPhonebook: List<Payload> = response.body()!!.payload
                   phoneBookCustomAdapter = PhonebookAdapter(applicationContext, listOfPhonebook)
                   recyclerView.adapter = phoneBookCustomAdapter
                   progressBar.visibility = View.GONE
               }else{
                   Log.e(TAG, response.toString())
               }
            }

            override fun onFailure(call: Call<Phonebook>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e(TAG, t.toString())
                toast("Error $t")
            }

        })

    }
}
