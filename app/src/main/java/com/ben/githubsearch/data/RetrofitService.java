package com.ben.githubsearch.data;

import com.ben.githubsearch.model.Owner;
import com.ben.githubsearch.model.SearchResult;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {

    //https://api.github.com/search/repositories?q=etsyclient
    //https://api.github.com/users/madben87/followers

    //https://api.github.com/search/repositories?q=tetris+language:assembly&sort=user&order=desc&page=1&per_page=10

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("search/repositories")
    Observable<SearchResult> getSearchResult(@Query("q") String query);

    @GET("search/repositories")
    Observable<SearchResult> getSearchResultPagination(@Query("q") String q, @Query("sort") String sort, @Query("order") String order,
                                                 @Query("page") int page, @Query("per_page") int perPage);

    @GET("users/{user}/followers")
    Observable<ArrayList<Owner>> getFollowers(@Path("user") String user);
}
