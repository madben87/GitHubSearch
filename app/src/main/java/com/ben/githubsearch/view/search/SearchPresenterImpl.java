package com.ben.githubsearch.view.search;

import com.ben.githubsearch.data.DataManager;
import com.ben.githubsearch.data.Repository;
import com.ben.githubsearch.model.SearchResult;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.util.MadLog;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observer;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class SearchPresenterImpl implements SearchPresenter, Constants {

    private SearchView view;
    private CompositeSubscription compositeSubscription;
    private Repository dataManager;

    @Inject
    public SearchPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void searchData(String query) {

        compositeSubscription.add(dataManager.searchData(query, START_PAGE)
        .subscribe(new Observer<SearchResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MadLog.error(this, e.getMessage());
            }

            @Override
            public void onNext(SearchResult searchResult) {
                view.showResult(searchResult);
                MadLog.log(this, "searchData");
            }
        }));
    }

    @Override
    public void attachView(SearchView mvpView) {
        view = mvpView;
        compositeSubscription = new CompositeSubscription();
        MadLog.log(this, "attachView");
    }

    @Override
    public void detachView() {
        view = null;
        unSubscribe();
        MadLog.log(this, "detachView");
    }

    private void unSubscribe() {
        if (compositeSubscription != null) {
            if (!compositeSubscription.isUnsubscribed()) {
                compositeSubscription.unsubscribe();
                compositeSubscription = null;
            }
        }
    }
}
