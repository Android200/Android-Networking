package com.httpurlcon.androidnetworking104;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Details extends AppCompatActivity {
    TextView login, Html_url, location;
    ImageView avatar_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        login = findViewById(R.id.d_login);
        Html_url = findViewById(R.id.d_html_url);
        location = findViewById(R.id.d_location);

        avatar_url = findViewById(R.id.d_avatar_url);

        Intent intent = getIntent();

        final String userName = intent.getStringExtra(GithubAdapter.KEY_NAME);
        final String html_url = intent.getStringExtra(GithubAdapter.KEY_URL);
        String image = intent.getStringExtra(GithubAdapter.KEY_IMAGE);

        RequestOptions placeholderRequest = new RequestOptions();
        placeholderRequest.placeholder(R.drawable.error);
        Glide.with(this).setDefaultRequestOptions(placeholderRequest).load(image).into(avatar_url);
        login.setText(userName);
        Html_url.setText(html_url);
        location.setText("Kano");

    }
}
