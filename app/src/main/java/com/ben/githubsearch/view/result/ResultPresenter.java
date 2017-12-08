package com.ben.githubsearch.view.result;

import android.support.v7.widget.RecyclerView;

import com.ben.githubsearch.Presenter;
import com.ben.githubsearch.model.SearchResult;

public interface ResultPresenter extends Presenter<ResultView> {

    void setPagination(RecyclerView recyclerView);

    void filterList(SearchResult searchResult);

    void showDialog();
}
