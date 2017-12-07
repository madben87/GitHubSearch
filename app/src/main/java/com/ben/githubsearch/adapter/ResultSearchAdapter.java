package com.ben.githubsearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ben.githubsearch.R;
import com.ben.githubsearch.model.Repository;
import com.ben.githubsearch.model.SearchResult;
import com.ben.githubsearch.util.Constants;
import com.ben.githubsearch.view.detail.DetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ResultSearchAdapter extends RecyclerView.Adapter<ResultSearchHolder> implements Constants {

    private SearchResult searchResult;
    private boolean singleLine = true;

    @Inject
    public Context context;

    @Inject
    public ResultSearchAdapter() {
        searchResult = new SearchResult();
    }

    public void setResult(SearchResult searchResult) {
        this.searchResult = searchResult;
    }

    public void addNewList(ArrayList<Repository> list) {
        if (list.size() == 0) {
            Toast.makeText(context, "No more goods", Toast.LENGTH_SHORT).show();
            return;
        }

        searchResult.getItems().addAll(list);
    }

    @Override
    public ResultSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);
        return new ResultSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultSearchHolder holder, int position) {

        holder.repoName.setText(searchResult.getItems().get(position).getName());
        holder.description.setText(searchResult.getItems().get(position).getDescription());
        holder.description.setSingleLine(true);
        holder.forks.setText(searchResult.getItems().get(position).getForks());

        holder.mainImageResItem.setImageResource(0);

        ImageLoader.getInstance().displayImage(searchResult.getItems().get(position).getOwner().getAvatarUrl(), holder.mainImageResItem);

        //MadLog.log(this, String.valueOf(ImageLoader.getInstance().getMemoryCache()));

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                switch (view.getId()) {
                    case R.id.item_card:
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra(REPOSITORY_KEY, searchResult.getItems().get(position));
                        view.getContext().startActivity(intent);
                        break;
                    case R.id.descr_layout:
                        singleLine = !singleLine;
                        holder.description.setSingleLine(singleLine);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (searchResult.getItems() != null) {
            return searchResult.getItems().size();
        }
        return 0;
    }
}
