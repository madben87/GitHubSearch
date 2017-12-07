package com.ben.githubsearch.data;

import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.SearchResult;

import java.util.ArrayList;

import rx.Observable;

public interface Repository {

    Observable<SearchResult> searchData(String query, int page);

    Observable<ArrayList<Owner>> getFollowers(String query);
}
