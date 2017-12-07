package com.ben.githubsearch.data;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.SearchResult;
import com.ben.githubsearch.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataManager implements Constants, Repository {

    private final RetrofitService retrofitService;

    @Inject
    public DataManager(RetrofitService retrofitService) {
        this.retrofitService = retrofitService;
        GitHubSearch.getInjector().inject(this);
    }

    @Override
    public Observable<ArrayList<Owner>> getFollowers(String query) {
        return retrofitService.getFollowers(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<SearchResult> searchData(String query, int page) {
        return retrofitService.getSearchResultPagination(query, SORT_BY_NAME, ORDER_DESC, page, PER_PAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
