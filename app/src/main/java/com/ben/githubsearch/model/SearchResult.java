package com.ben.githubsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ben.githubsearch.util.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchResult implements Constants, Parcelable {

    @SerializedName(TOTAL_COUNT)
    private int totalCount;
    @SerializedName(INCOMPLETE_RESULT)
    private boolean incompleteResults;
    @SerializedName(ITEMS)
    private ArrayList<Repository> items;

    public SearchResult() {
    }

    protected SearchResult(Parcel in) {
        totalCount = in.readInt();
        incompleteResults = in.readByte() != 0;
        items = in.createTypedArrayList(Repository.CREATOR);
    }

    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel in) {
            return new SearchResult(in);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public ArrayList<Repository> getItems() {
        return items;
    }

    public void setItems(ArrayList<Repository> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(totalCount);
        dest.writeByte((byte) (incompleteResults ? 1 : 0));
        dest.writeTypedList(items);
    }
}
