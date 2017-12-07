package com.ben.githubsearch.view.search;

import com.ben.githubsearch.Presenter;

public interface SearchPresenter extends Presenter<SearchView> {

    void searchData(String query);
}
