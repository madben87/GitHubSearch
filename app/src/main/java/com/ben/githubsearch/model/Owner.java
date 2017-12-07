package com.ben.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.githubsearch.util.Constants;
import com.google.gson.annotations.SerializedName;

public class Owner implements Constants, Parcelable {

    @SerializedName(LOGIN)
    private String login;
    @SerializedName(OWNER_ID)
    private int ownerId;
    @SerializedName(AVATAR_URL)
    private String avatarUrl;
    @SerializedName(OWNER_HTML_URL)
    private String ownerHtmlUrl;

    public Owner() {}

    protected Owner(Parcel in) {
        login = in.readString();
        ownerId = in.readInt();
        avatarUrl = in.readString();
        ownerHtmlUrl = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOwnerHtmlUrl() {
        return ownerHtmlUrl;
    }

    public void setOwnerHtmlUrl(String ownerHtmlUrl) {
        this.ownerHtmlUrl = ownerHtmlUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(ownerId);
        dest.writeString(avatarUrl);
        dest.writeString(ownerHtmlUrl);
    }
}
