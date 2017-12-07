package com.ben.githubsearch.view.detail;

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
    /*@BindView(R.id.detail_forks_btn)
    LinearLayout detailForksBtn;
    @BindView(R.id.detail_followers_btn)
    LinearLayout detailFollowersBtn;*/

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
    }

    @Override
    public void showDetail(RepoDetail repoDetail) {

        detailRepoName.setText(repoDetail.getRepository().getFullName());
        imageLoader.displayImage(repoDetail.getRepository().getOwner().getAvatarUrl(), detailAvatar);
        detailDescription.setText(repoDetail.getRepository().getDescription());
        detailForks.setText(repoDetail.getRepository().getForks());
        detailFollowers.setText(String.valueOf(repoDetail.getFollowersList().size()));
    }

    @Override
    public void showFollowers(ArrayList<Owner> followersList) {
        detailFollowers.setText(followersList.size());
    }

    @OnClick(R.id.detail_followers_btn)
    void click(View view) {
        if (getIntent() != null) {
            repository = getIntent().getParcelableExtra(REPOSITORY_KEY);
            detailPresenter.getFollowers(repository.getOwner());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        detailPresenter.detachView();
    }
}
