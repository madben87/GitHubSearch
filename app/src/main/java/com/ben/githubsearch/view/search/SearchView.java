package com.ben.githubsearch.view.search;

import com.ben.githubsearch.MVPView;
import com.ben.githubsearch.model.SearchResult;

public interface SearchView extends MVPView {

    void showResult(SearchResult searchResult);
}
