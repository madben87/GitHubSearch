package com.ben.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class RepoDetail implements Parcelable {

    private Repository repository;
    private ArrayList<Owner> followersList;

    public RepoDetail() {}

    protected RepoDetail(Parcel in) {
        repository = in.readParcelable(Repository.class.getClassLoader());
        followersList = in.createTypedArrayList(Owner.CREATOR);
    }

    public static final Creator<RepoDetail> CREATOR = new Creator<RepoDetail>() {
        @Override
        public RepoDetail createFromParcel(Parcel in) {
            return new RepoDetail(in);
        }

        @Override
        public RepoDetail[] newArray(int size) {
            return new RepoDetail[size];
        }
    };

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public ArrayList<Owner> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<Owner> followersList) {
        this.followersList = followersList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(repository, flags);
        dest.writeTypedList(followersList);
    }
}
