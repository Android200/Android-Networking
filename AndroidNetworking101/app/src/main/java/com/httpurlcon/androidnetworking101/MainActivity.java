package com.httpurlcon.androidnetworking101;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.widget.LinearLayout.VERTICAL;


public class MainActivity extends AppCompatActivity {

    //First string variable contains the URL from which we will get the JSON data.
    private String jsonURL = "https://auna.000webhostapp.com/api/JSON1.php";
    private final int jsoncode = 1;
    private RecyclerView recyclerView;
    ArrayList<RogerModel> rogerModelArrayList;
    private RogerAdapter rogerAdapter;
    private static ProgressDialog mProgressDialog;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);

        fetchJSON();
    }

   /* This is the method where compiler will make an http call.
    Compiler will use AsyncTask class to make an http call with required parameters and URL.
    When the http call is done successfully, compiler will execute onPostExecute() method.
    onPostExecute() method will call the onTaskCompleted() method.*/
    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(){

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest(jsonURL);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss",result);
                onTaskCompleted(result,jsoncode);
            }
        }.execute();
    }


  /*  first, compiler will check if the status of JSON response is true or false.
    If the status is true then compiler will go inside the if condition.
    Then it will get the information from the getInfo() method.*/
    @SuppressLint("WrongConstant")
    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:

                if (isSuccess(response)) {
                    removeSimpleProgressDialog();  //will remove progress dialog
                    rogerModelArrayList = getInfo(response);
                    rogerAdapter = new RogerAdapter(this,rogerModelArrayList);
                    recyclerView.setAdapter(rogerAdapter);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), VERTICAL, false));


                }else {
                    removeSimpleProgressDialog();
                    Toast.makeText(MainActivity.this, getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }


   /* Compiler will rotate the for loop for the number of iterations equals
    to the number of objects present in the JSONArray named “data”
    After getting all the data, compiler will send it to the adapter
    class and adapter will finally populate the recyclerview.*/
    public ArrayList<RogerModel> getInfo(String response) {
        ArrayList<RogerModel> tennisModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                JSONArray dataArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {

                    RogerModel playersModel = new RogerModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    playersModel.setName(dataobj.getString("name"));
                    playersModel.setCountry(dataobj.getString("country"));
                    playersModel.setCity(dataobj.getString("city"));
                    playersModel.setImgURL(dataobj.getString("imgURL"));
                    tennisModelArrayList.add(playersModel);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tennisModelArrayList;
    }

    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString("status").equals("true")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title, String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
