package com.codepath.apps.restclienttemplate;

import static com.codepath.apps.restclienttemplate.R.drawable.ic_launcher_twitter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.json.JSONArray;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

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
        TextView tvBody;
        TextView tvName;
        TextView tvUserName;
        TextView tvTimeStamp;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvName = itemView.findViewById(R.id.tvName);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            ivTweetImage =  itemView.findViewById(R.id.ivTweetImage);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);



        }

        public void bind(Tweet tweet) {
            tvName.setText(tweet.user.name);
            tvUserName.setText(tweet.user.screenName);
            tvBody.setText(tweet.body);
            tvTimeStamp.setText(tweet.timeStamp);

            int radius = 50;
            Glide.with(context).load(tweet.mediaUrl).into(ivTweetImage);
            Glide.with(context).load(tweet.user.profileImageUrl).transform(new RoundedCorners(radius)).into(ivProfileImage);
        }
    }

}
