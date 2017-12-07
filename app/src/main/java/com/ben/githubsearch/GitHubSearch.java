package com.ben.githubsearch;

import android.app.Application;
import android.content.Context;

import com.ben.githubsearch.modules.ContextModule;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class GitHubSearch extends Application {

    private Injector injector;

    private static GitHubSearch gitHubSearchInstance;

    private static String query;

    public static GitHubSearch getInstance() {
        return gitHubSearchInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        gitHubSearchInstance = this;

        //MadLog.setDebugMode(true);

        injector = DaggerInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();

        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.defaultDisplayImageOptions (options);
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String query) {
        GitHubSearch.query = query;
    }

    public static Injector getInjector() {
        return getInstance().injector;
    }
}
