package com.ben.githubsearch.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ben.githubsearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.follow_card)
    CardView followCard;
    @BindView(R.id.follow_name)
    TextView followName;
    @BindView(R.id.follow_avatar)
    ImageView followAvatar;

    public FollowersHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
