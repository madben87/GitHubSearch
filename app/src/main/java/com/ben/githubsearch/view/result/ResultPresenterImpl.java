package com.ben.githubsearch.view.result;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.data.DataManager;
import com.ben.githubsearch.data.Repository;
import com.ben.githubsearch.model.SearchResult;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.util.MadLog;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Observer;
import rx.subscriptions.CompositeSubscription;

@Singleton
public class ResultPresenterImpl implements ResultPresenter, Constants {

    private int firstVisibleItems, visibleItemCount, totalItemCount, nextPage;
    private boolean loading;
    private ResultView view;
    private CompositeSubscription compositeSubscription;
    private Repository dataManager;

    @Inject
    public ResultPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Observable<SearchResult> loadNextPage(String query) {
        nextPage++;
        return dataManager.searchData(query, nextPage);
    }

    @Override
    public void setPagination(RecyclerView recyclerView) {

        final LinearLayoutManager layoutManager;

        layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        nextPage = START_PAGE;

        MadLog.log(this, "setPagination");

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    firstVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    //if (totalItemCount < )

                    if (!loading && (visibleItemCount + firstVisibleItems) >= totalItemCount) {

                        loading = !loading;

                        compositeSubscription.add(loadNextPage(GitHubSearch.getQuery())
                        .subscribe(new Observer<SearchResult>() {
                            @Override
                            public void onCompleted() {
                                loading = !loading;
                            }

                            @Override
                            public void onError(Throwable e) {
                                MadLog.error(this, e.getMessage());
                            }

                            @Override
                            public void onNext(SearchResult searchResult) {
                                view.nextPage(searchResult.getItems());
                                MadLog.log(this, "loadNextPage");
                            }
                        }));
                    }
                }
            }
        });
    }

    @Override
    public void attachView(ResultView mvpView) {
        this.view = mvpView;
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
