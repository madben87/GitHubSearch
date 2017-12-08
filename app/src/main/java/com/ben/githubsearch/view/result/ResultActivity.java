package com.ben.githubsearch.view.result;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.R;
import com.ben.githubsearch.adapter.ResultSearchAdapter;
import com.ben.githubsearch.model.Repository;
import com.ben.githubsearch.model.SearchResult;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.util.MadLog;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity implements Constants, ResultView {

    private SearchResult searchResult;

    @BindView(R.id.result_rec_list)
    RecyclerView resSearchList;

    @Inject
    ResultSearchAdapter resultSearchAdapter;
    @Inject
    ResultPresenterImpl resultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        GitHubSearch.getInjector().inject(this);

        ButterKnife.bind(this);

        resultPresenter.attachView(this);

        if (getIntent() != null) {

            searchResult = getIntent().getParcelableExtra(RESULT_KEY);
            showResult(searchResult);
        }

        MadLog.log(this, "onCreate");
    }

    @Override
    public void showResult(SearchResult searchResult) {

        resultSearchAdapter.setResult(searchResult);
        resSearchList.setHasFixedSize(true);
        resSearchList.setLayoutManager(new LinearLayoutManager(this));
        resSearchList.setAdapter(resultSearchAdapter);
        resultPresenter.setPagination(resSearchList);

        MadLog.log(this, "showResult");
    }

    @Override
    public void nextPage(ArrayList<Repository> list) {

        resultSearchAdapter.addNewList(list);
        resultSearchAdapter.notifyDataSetChanged();

        MadLog.log(this, "nextPage");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        resultPresenter.detachView();
        MadLog.log(this, "onDestroy");
    }

    @OnClick(R.id.btn_filter)
    void click(View view) {
        resultPresenter.showDialog();
    }
}
