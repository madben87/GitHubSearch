package com.ben.githubsearch.view.followers;

import com.ben.githubsearch.MVPView;
import com.ben.githubsearch.model.Owner;

import java.util.ArrayList;

public interface FollowersView extends MVPView {

    void showFollowers(ArrayList<Owner> list);
}
