package com.ben.githubsearch.view.detail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.R;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.RepoDetail;
import com.ben.githubsearch.model.Repository;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.util.MadLog;
import com.ben.githubsearch.view.followers.FollowersActivity;
import com.ben.githubsearch.view.owner.CustomTab;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DetailView, Constants {

    @BindView(R.id.detail_repo_name)
    TextView detailRepoName;
    @BindView(R.id.detail_owner_login)
    TextView detailOwnerLogin;
    @BindView(R.id.detail_description)
    TextView detailDescription;
    @BindView(R.id.detail_forks)
    TextView detailForks;
    @BindView(R.id.detail_followers)
    TextView detailFollowers;
    @BindView(R.id.detail_item_card)
    CardView detailItemCard;
    @BindView(R.id.detail_avatar)
    ImageView detailAvatar;

    private ImageLoader imageLoader;
    private Repository repository;

    @Inject
    DetailPresenterImpl detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        GitHubSearch.getInjector().inject(this);

        detailPresenter.attachView(this);

        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));

        if (getIntent() != null) {
            repository = getIntent().getParcelableExtra(REPOSITORY_KEY);
            detailPresenter.showRepo(repository);
        }

        MadLog.log(this, "onCreate");
    }

    @Override
    public void showDetail(RepoDetail repoDetail) {

        detailRepoName.setText(repoDetail.getRepository().getFullName());
        detailOwnerLogin.setText(repoDetail.getRepository().getOwner().getLogin());
        imageLoader.displayImage(repoDetail.getRepository().getOwner().getAvatarUrl(), detailAvatar);
        detailDescription.setText(repoDetail.getRepository().getDescription());
        detailForks.setText(repoDetail.getRepository().getForks());
        detailFollowers.setText(String.valueOf(repoDetail.getFollowersList().size()));

        MadLog.log(this, "showDetail");
    }

    @Override
    public void showFollowers(ArrayList<Owner> followersList) {

        Intent intent = new Intent(this, FollowersActivity.class);
        intent.putExtra(FOLLOWERS_KEY, followersList);
        this.startActivity(intent);

        MadLog.log(this, "showFollowers");
    }

    @OnClick({R.id.detail_followers_btn, R.id.detail_qwner_info, R.id.detail_avatar})
    void click(View view) {

        switch (view.getId()) {
            case R.id.detail_followers_btn:
                detailPresenter.getFollowers(repository.getOwner());
                MadLog.log(this, "click detail_followers_btn");
                break;
            case R.id.detail_qwner_info:
                CustomTab.tabShow(this, Uri.parse(repository.getOwner().getOwnerHtmlUrl()));
                break;
            case R.id.detail_avatar:
                CustomTab.tabShow(this, Uri.parse(repository.getOwner().getOwnerHtmlUrl()));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        detailPresenter.detachView();
        MadLog.log(this, "onDestroy");
    }
}
