package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;
    public String TAG = "TweetsAdapter";


    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        holder.bind(tweet);
    }

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweet) {
        tweets.addAll(tweet);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        ImageView ivTweetImage;
        ImageView ivVerified;
        ImageView ivFavorite;
        ImageView ivRetweet;
        TextView tvBody;
        TextView tvName;
        TextView tvUserName;
        TextView tvTimeStamp;
        TextView tvReplyCount;
        TextView tvRetweetCount;
        TextView tvFavoriteCount;
        Boolean isLiked = false;
        Boolean isRetweeted = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivVerified = itemView.findViewById(R.id.ivVerified);
            ivFavorite = itemView.findViewById(R.id.ivFavorite);
            ivRetweet = itemView.findViewById(R.id.ivRetweet);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivTweetImage =  itemView.findViewById(R.id.ivTweetImage);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            tvReplyCount = itemView.findViewById(R.id.tvReplyCount);
            tvRetweetCount = itemView.findViewById(R.id.tvRetweetCount);
            tvFavoriteCount = itemView.findViewById(R.id.tvFavoriteCount);


            ivFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show();

                    if (isLiked) {
                        isLiked = false;
                        ivFavorite.setImageResource(R.drawable.ic_vector_heart_stroke);
                    } else {
                        isLiked = true;
                        ivFavorite.setImageResource(R.drawable.ic_vector_heart);
                    }


                }
            });


            ivRetweet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show();

                    if (isRetweeted) {
                        isRetweeted = false;
                        ivRetweet.setImageResource(R.drawable.ic_vector_retweet_stroke);
                    } else {
                        isRetweeted = true;
                        ivRetweet.setImageResource(R.drawable.ic_vector_retweet);
                    }


                }
            });


            itemView.setOnClickListener((v) ->  {
                int position = getAdapterPosition();
//                Toast.makeText(context, "Nice", Toast.LENGTH_SHORT).show();
                if (position != RecyclerView.NO_POSITION) {
                    Tweet tweet = tweets.get(position);

                    Intent intent = new Intent(context, TweetDetailsActivity.class);

                    intent.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));

                    context.startActivity(intent);
                }
            });







        }


        public void bind(Tweet tweet) {
            tvName.setText(tweet.user.name);
            if ((tweet.user.name).length() < 20) {
                tvUserName.setText(tweet.user.screenName);
            }
            tvBody.setText(tweet.body);
            tvTimeStamp.setText(tweet.timeStamp);

            tvReplyCount.setText(tweet.replyCount);
            tvRetweetCount.setText(tweet.retweetCount);
            tvFavoriteCount.setText(tweet.favoriteCount);




            int profileRadius = 80;
            Glide.with(context).load(tweet.mediaUrl).into(ivTweetImage);
            Glide.with(context).load(tweet.user.profileImageUrl).transform(new RoundedCorners(profileRadius)).into(ivProfileImage);
            Log.i("Verified", tweet.user.name + ":  " +  tweet.user.verified.toString());
            Log.i("Verified", tweet.user.name + ":  " +  tvName.getWidth());
            if (tweet.user.verified) {
                ivVerified.setVisibility(View.VISIBLE);
                ivVerified.setImageResource(R.drawable.ic_vector_verified);
            } else {
                ivVerified.setVisibility(View.GONE);
                ivVerified.setImageResource(0);
            }
        }





    }

}
