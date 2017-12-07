package com.ben.githubsearch.view.owner;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;

import com.ben.githubsearch.R;

public class CustomTab {

    public static void tabShow(Context context, Uri uri) {
        //Chrome Tabs example
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(context.getResources().getColor(R.color.colorPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, uri);
    }
}
