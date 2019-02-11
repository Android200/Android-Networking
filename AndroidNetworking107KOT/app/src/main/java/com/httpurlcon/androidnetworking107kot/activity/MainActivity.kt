package com.httpurlcon.androidnetworking107kot.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.httpurlcon.androidnetworkin106kot.util.ListItemDecorations
import com.httpurlcon.androidnetworking107kot.R
import com.httpurlcon.androidnetworking107kot.adapter.ContactsAdapter
import com.httpurlcon.androidnetworking107kot.mdel.contacts
import com.httpurlcon.androidnetworking107kot.mdel.contactsResponse
import com.httpurlcon.androidnetworking107kot.rest.APIClient
import com.httpurlcon.androidnetworking107kot.rest.APIinterface
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.simpleName

    lateinit var contactCustomAdapter: ContactsAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)

        progressBar.visibility = View.VISIBLE

        var apiServices = APIClient.client.create(APIinterface::class.java)

        val call = apiServices.getContact()

        call.enqueue(object: Callback<contactsResponse> {

            override fun onResponse(call: Call<contactsResponse>, response: Response<contactsResponse>) {
                if(response.isSuccessful){
                    var listOfContacts: List<contacts> = response.body()!!.contacts
                    contactCustomAdapter = ContactsAdapter(applicationContext,listOfContacts)
                    recyclerView.adapter = contactCustomAdapter
                    progressBar.visibility = View.GONE
                }else{
                    Log.e(TAG, response.toString())
                }
            }

            override fun onFailure(call: Call<contactsResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e(TAG, t.toString())
                toast("Error $t")
            }

        })


    }
}
