package com.ben.githubsearch.view.detail;

import com.ben.githubsearch.data.DataManager;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.RepoDetail;
import com.ben.githubsearch.model.Repository;
import com.ben.githubsearch.util.Constants;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.subscriptions.CompositeSubscription;

public class DetailPresenterImpl implements DetailPresenter, Constants {

    private DetailView view;
    private ArrayList<Owner> followersList;
    private RepoDetail repoDetail;
    private DataManager dataManager;
    private CompositeSubscription compositeSubscription;

    @Inject
    public DetailPresenterImpl(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void showRepo(final Repository repository) {

        compositeSubscription.add(dataManager.getFollowers(repository.getOwner().getLogin())
                .subscribe(new Observer<ArrayList<Owner>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<Owner> owners) {
                        followersList = owners;
                        repoDetail = new RepoDetail();
                        repoDetail.setRepository(repository);
                        repoDetail.setFollowersList(followersList);

                        view.showDetail(repoDetail);
                    }
                }));
    }

    @Override
    public void getFollowers(Owner owner) {

        compositeSubscription.add(dataManager.getFollowers(owner.getLogin())
                .subscribe(new Observer<ArrayList<Owner>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ArrayList<Owner> owners) {

                        view.showFollowers(owners);
                    }
                }));
    }

    @Override
    public void attachView(DetailView mvpView) {
        view = mvpView;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        view = null;
        unSubscribe();
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
