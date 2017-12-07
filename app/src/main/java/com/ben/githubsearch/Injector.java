package com.ben.githubsearch;

import com.ben.githubsearch.data.DataManager;
import com.ben.githubsearch.modules.ContextModule;
import com.ben.githubsearch.modules.RetrofitModule;
import com.ben.githubsearch.view.detail.DetailActivity;
import com.ben.githubsearch.view.result.ResultActivity;
import com.ben.githubsearch.view.search.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface Injector {

    void inject(SearchActivity searchActivity);
    void inject(DataManager dataManager);
    void inject(ResultActivity resultActivity);
    void inject(DetailActivity detailActivity);
}
