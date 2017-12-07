package com.ben.githubsearch.view.detail;

import com.ben.githubsearch.Presenter;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.Repository;

public interface DetailPresenter extends Presenter<DetailView> {

    void showRepo(Repository repository);

    void getFollowers(Owner owner);
}
