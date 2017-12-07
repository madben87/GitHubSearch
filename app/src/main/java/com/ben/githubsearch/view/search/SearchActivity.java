package com.ben.githubsearch.view.search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.R;
import com.ben.githubsearch.model.SearchResult;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.util.MadLog;
import com.ben.githubsearch.view.result.ResultActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchView, Constants {

    @Inject
    SearchPresenterImpl searchPresenter;

    @BindView(R.id.search_text)
    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        GitHubSearch.getInjector().inject(this);

        searchPresenter.attachView(this);

        MadLog.log(this, "onCreate");
    }

    @Override
    public void showResult(SearchResult searchResult) {

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, searchResult);
        startActivity(intent);

        MadLog.log(this, "showResult");
    }

    @OnClick(R.id.search_btn)
    void click(View view) {

        if (searchText.getText() != null && !searchText.getText().toString().equals("")) {
            GitHubSearch.setQuery(searchText.getText().toString());
            searchPresenter.searchData(GitHubSearch.getQuery());
        }else {
            Toast.makeText(this, "Please, enter your query", Toast.LENGTH_SHORT).show();
        }

        MadLog.log(this, "click search_btn");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        searchPresenter.detachView();
        MadLog.log(this, "onDestroy");
    }
}
