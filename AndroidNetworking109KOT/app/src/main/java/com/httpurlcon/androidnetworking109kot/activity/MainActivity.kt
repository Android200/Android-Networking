package com.httpurlcon.androidnetworking109kot.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.httpurlcon.androidnetworking109kot.R
import com.httpurlcon.androidnetworking109kot.adapter.MoviesAdapter
import com.httpurlcon.androidnetworking109kot.models.Movie
import com.httpurlcon.androidnetworking109kot.models.Movy
import com.httpurlcon.androidnetworking109kot.rest.APIClient
import com.httpurlcon.androidnetworking109kot.rest.APIinterface
import com.httpurlcon.androidnetworking109kot.util.ListItemDecorations
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG: String = MainActivity::class.java.simpleName

    lateinit var movieCustomAdapter: MoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)

        progressBar.visibility = View.VISIBLE

        var apiServices = APIClient.client.create(APIinterface::class.java)

        val call = apiServices.getMovies()


        call.enqueue(object : Callback<Movie>{

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    var listOfMovies: List<Movy> = response.body()!!.movies
                    movieCustomAdapter = MoviesAdapter(applicationContext,listOfMovies)

                    recyclerView.adapter = movieCustomAdapter
                    progressBar.visibility = View.GONE
                }else{
                    Log.e(TAG, response.toString())
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e(TAG, t.toString())
                toast("Error $t")            }

        })




    }
}
