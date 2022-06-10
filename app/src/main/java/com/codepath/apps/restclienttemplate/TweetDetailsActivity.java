package com.codepath.apps.restclienttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.databinding.ActivityTweetDetailsBinding;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {


    private String TAG = "TweetDetailsActivity";
    private ActivityTweetDetailsBinding binding;


    Tweet tweet;
    ImageView ivDetailsProfileImage;
    ImageView ivDetailsVerified;
    ImageView ivDetailsTweetImage;
    ImageView ivDetailsReply;
    ImageView ivDetailsRetweet;
    ImageView ivDetailsFavorite;
    TextView tvDetailsName;
    TextView tvDetailsUserName;
    TextView tvDetailsBody;
    TextView tvDetailsReplyCount;
    TextView tvDetailsRetweetCount;
    TextView tvDetailsFavoriteCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTweetDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        tweet = (Tweet) Parcels.unwrap(getIntent().getParcelableExtra(Tweet.class.getSimpleName()));

//        Log.i(TAG, "Tweet is: " + t);


        ivDetailsProfileImage = binding.ivDetailsProfileImage;
        ivDetailsVerified = binding.ivDetailsVerified;
        ivDetailsTweetImage = binding.ivDetailsTweetImage;
        tvDetailsName = binding.tvDetailsName;
        tvDetailsUserName = binding.tvDetailsUserName;
        tvDetailsBody = binding.tvDetailsBody;
        ivDetailsReply = binding.ivDetailsReply;
        ivDetailsRetweet = binding.ivDetailsRetweet;
        ivDetailsFavorite = binding.ivDetailsFavorite;
        tvDetailsReplyCount = binding.tvDetailsReplyCount;
        tvDetailsRetweetCount = binding.tvDetailsRetweetCount;
        tvDetailsFavoriteCount = binding.tvDetailsFavoriteCount;



        tvDetailsName.setText(tweet.user.name);
        tvDetailsUserName.setText(tweet.user.screenName);
        tvDetailsBody.setText(tweet.body);
        tvDetailsReplyCount.setText(tweet.replyCount);
        tvDetailsRetweetCount.setText(tweet.retweetCount);
        tvDetailsFavoriteCount.setText(tweet.favoriteCount);

        int profileRadius = 80;
        Glide.with(this).load(tweet.user.profileImageUrl).transform(new RoundedCorners(profileRadius)).into(ivDetailsProfileImage);
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