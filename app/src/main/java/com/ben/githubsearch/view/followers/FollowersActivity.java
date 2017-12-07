package com.ben.githubsearch.view.followers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.R;
import com.ben.githubsearch.adapter.FollowersAdapter;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowersActivity extends AppCompatActivity implements Constants, FollowersView {

    @BindView(R.id.rec_followers_list)
    RecyclerView recFollowersList;

    @Inject
    FollowersAdapter followersAdapter;

    private ArrayList<Owner> followers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ButterKnife.bind(this);

        GitHubSearch.getInjector().inject(this);

        if (getIntent() != null) {
            followers = getIntent().getParcelableArrayListExtra(FOLLOWERS_KEY);
            showFollowers(followers);
        }
    }

    @Override
    public void showFollowers(ArrayList<Owner> list) {

        followersAdapter.setFollowers(list);
        recFollowersList.setHasFixedSize(true);
        recFollowersList.setLayoutManager(new LinearLayoutManager(this));
        recFollowersList.setAdapter(followersAdapter);
    }
}
