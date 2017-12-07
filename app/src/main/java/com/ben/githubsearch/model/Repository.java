package com.ben.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.githubsearch.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Repository implements Constants, Parcelable {

    @SerializedName(ID)
    private long id;
    @SerializedName(NAME)
    private String name;
    @SerializedName(FULL_NAME)
    private String fullName;
    @SerializedName(OWNER)
    private Owner owner;
    @SerializedName(HTML_URL)
    private String htmlUrl;
    @SerializedName(DESCRIPTION)
    private String description;
    @SerializedName(SUBSCRIBERS_URL)
    private String subscribersUrl;
    @SerializedName(FORKS)
    private String forks;

    public Repository() {}

    protected Repository(Parcel in) {
        id = in.readLong();
        name = in.readString();
        fullName = in.readString();
        owner = in.readParcelable(Owner.class.getClassLoader());
        htmlUrl = in.readString();
        description = in.readString();
        subscribersUrl = in.readString();
        forks = in.readString();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeParcelable(owner, flags);
        dest.writeString(htmlUrl);
        dest.writeString(description);
        dest.writeString(subscribersUrl);
        dest.writeString(forks);
    }
}
