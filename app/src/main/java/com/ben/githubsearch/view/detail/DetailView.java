package com.ben.githubsearch.view.detail;

import com.ben.githubsearch.MVPView;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.RepoDetail;
import com.ben.githubsearch.model.Repository;

import java.util.ArrayList;

public interface DetailView extends MVPView {

    void showDetail(RepoDetail repoDetail);

    void showFollowers(ArrayList<Owner> followersList);
}
