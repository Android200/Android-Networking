package com.httpurlcon.androidnetworking110kot.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.httpurlcon.androidnetworking110kot.R
import com.httpurlcon.androidnetworking110kot.adapter.MovieAdapter
import com.httpurlcon.androidnetworking110kot.models.Stories
import com.httpurlcon.androidnetworking110kot.models.Story
import com.httpurlcon.androidnetworking110kot.rest.APIClient
import com.httpurlcon.androidnetworking110kot.rest.APIinterface
import com.httpurlcon.androidnetworking110kot.util.ListItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG: String = MainActivity::class.java.simpleName

    lateinit var movieCustomAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecoration(20))
        recyclerView.setHasFixedSize(true)

        progressBar.visibility = View.VISIBLE

        var apiServices = APIClient.client.create(APIinterface::class.java)

        val call = apiServices.getMoviees()

        call.enqueue(object : Callback<Stories> {
            override fun onFailure(call: Call<Stories>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e(TAG, t.toString())
                longToast("Error: $t")
            }

            override fun onResponse(call: Call<Stories>, response: Response<Stories>) {
                if(response.isSuccessful){
                    var listOfMovies: List<Story> = response.body()!!.stories
                    movieCustomAdapter = MovieAdapter(applicationContext,listOfMovies)
                    recyclerView.adapter = movieCustomAdapter
                    progressBar.visibility = View.GONE
                }else{
                    Log.e(TAG, response.toString())
                }
            }

        })


    }
}
