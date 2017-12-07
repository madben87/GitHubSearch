package com.ben.githubsearch;

public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);
    void detachView();
}
