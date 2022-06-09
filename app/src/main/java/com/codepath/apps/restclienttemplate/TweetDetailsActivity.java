package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {


    private String TAG = "TweetDetailsActivity";

    Tweet tweet;
    ImageView ivDetailsProfileImage;
    ImageView ivDetailsVerified;
    ImageView ivDetailsTweetImage;
    TextView tvDetailsName;
    TextView tvDetailsUserName;
    TextView tvDetailsBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));


        ivDetailsProfileImage = findViewById(R.id.ivDetailsProfileImage);
        ivDetailsVerified = findViewById(R.id.ivDetailsVerified);
        ivDetailsTweetImage = findViewById(R.id.ivDetailsTweetImage);
        tvDetailsName = findViewById(R.id.tvDetailsName);
        tvDetailsUserName = findViewById(R.id.tvDetailsUserName);
        tvDetailsBody = findViewById(R.id.tvDetailsBody);



        tvDetailsName.setText(tweet.user.name);
        tvDetailsUserName.setText(tweet.user.screenName);
        tvDetailsBody.setText(tweet.body);

        Glide.with(this).load(tweet.user.profileImageUrl).into(ivDetailsProfileImage);
        Glide.with(this).load(tweet.mediaUrl).into(ivDetailsTweetImage);

        if (tweet.user.verified) {
            ivDetailsVerified.setVisibility(View.VISIBLE);
            ivDetailsVerified.setImageResource(R.drawable.ic_vector_verified);
        } else {
            ivDetailsVerified.setVisibility(View.GONE);
            ivDetailsVerified.setImageResource(0);
        }



    }
}