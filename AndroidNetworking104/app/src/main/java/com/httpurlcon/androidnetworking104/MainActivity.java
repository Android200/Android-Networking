package com.httpurlcon.androidnetworking104;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.ProgressDialog;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final String URL_DATA = "https://api.github.com/search/users?q=language:java+location:kano";
    ImageView imageView;
    TextView error;
    AnimatedVectorDrawableCompat avd;
    Animatable animatable;

    private RecyclerView.Adapter adapter;
    private List<githubmodel> githubmodelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        imageView = findViewById(R.id.imageView);
        error = findViewById(R.id.error);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         avd = AnimatedVectorDrawableCompat.create(MainActivity.this, R.drawable.avd_loading_bar);
        imageView.setImageDrawable(avd);
        animatable = (Animatable) imageView.getDrawable();
        githubmodelList = new ArrayList<>();
        loadUrlData();

    }

    private void loadUrlData(){
        /*final ProgressDialog progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setTitle("Github");
        progressDialog.setCancelable(false);
        progressDialog.show();*/
        animatable.start();
        imageView.setVisibility(View.VISIBLE);
        error.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressDialog.dismiss();
                animatable.stop();
                error.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("items");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jo = array.getJSONObject(i);
                        githubmodel model = new githubmodel(jo.getString("login"),
                                jo.getString("avatar_url"),
                                jo.getString("html_url"));
                        githubmodelList.add(model);
                    }
                    adapter = new GithubAdapter(githubmodelList, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError errors) {

                //progressDialog.dismiss();
                animatable.stop();
                error.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error " + errors.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue  = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }





}
