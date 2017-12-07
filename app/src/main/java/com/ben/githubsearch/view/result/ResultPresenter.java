package com.ben.githubsearch.view.result;

import android.support.v7.widget.RecyclerView;

import com.ben.githubsearch.Presenter;

public interface ResultPresenter extends Presenter<ResultView> {

    void setPagination(RecyclerView recyclerView);
}
