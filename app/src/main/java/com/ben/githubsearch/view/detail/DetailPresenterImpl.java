package com.ben.githubsearch.view.detail;

import com.ben.githubsearch.data.DataManager;
import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.RepoDetail;
import com.ben.githubsearch.model.Repository;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.util.MadLog;

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
                        MadLog.error(this, e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Owner> owners) {
                        followersList = owners;
                        repoDetail = new RepoDetail();
                        repoDetail.setRepository(repository);
                        repoDetail.setFollowersList(followersList);

                        view.showDetail(repoDetail);
                        MadLog.log(this, "showRepo");
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
                        MadLog.error(this, e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Owner> owners) {

                        view.showFollowers(owners);
                        MadLog.log(this, "getFollowers");
                    }
                }));
    }

    @Override
    public void attachView(DetailView mvpView) {
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
