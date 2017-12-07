package com.ben.githubsearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.githubsearch.R;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.util.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FollowersAdapter extends RecyclerView.Adapter<FollowersHolder> implements Constants {

    private ArrayList<Owner> followers;

    @Inject
    public Context context;

    @Inject
    public FollowersAdapter() {
        followers = new ArrayList<>();
    }

    public void setFollowers(ArrayList<Owner> list) {
        this.followers = list;
    }

    @Override
    public FollowersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.follower_item, parent, false);
        return new FollowersHolder(view);
    }

    @Override
    public void onBindViewHolder(final FollowersHolder holder, int position) {

        holder.followName.setText(followers.get(position).getLogin());

        holder.followAvatar.setImageResource(0);

        ImageLoader.getInstance().displayImage(followers.get(position).getAvatarUrl(), holder.followAvatar);
    }

    @Override
    public int getItemCount() {
        if (followers != null) {
            return followers.size();
        }
        return 0;
    }
}
