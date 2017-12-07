package com.ben.githubsearch.view.result;

import com.ben.githubsearch.MVPView;
import com.ben.githubsearch.model.Repository;
import com.ben.githubsearch.model.SearchResult;

import java.util.ArrayList;

public interface ResultView extends MVPView {

    void showResult(SearchResult searchResult);

    void nextPage(ArrayList<Repository> list);
}
