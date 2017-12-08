package com.ben.githubsearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ben.githubsearch.GitHubSearch;
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

    private boolean singleLine = true;
    private ArrayList<Repository> repoList;

    @Inject
    public Context context;

    @Inject
    public ResultSearchAdapter() {
        repoList = new ArrayList<>();
    }

    public void setResult(SearchResult searchResult) {
        repoList = filterByName(searchResult.getItems());
    }

    public void addNewList(ArrayList<Repository> list) {
        if (list.size() == 0) {
            Toast.makeText(context, "No more goods", Toast.LENGTH_SHORT).show();
            return;
        }

        repoList.addAll(filterByName(list));
    }

    @Override
    public ResultSearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);
        return new ResultSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(final ResultSearchHolder holder, int position) {

        holder.repoName.setText(repoList.get(position).getName());
        holder.description.setText(repoList.get(position).getDescription());
        holder.description.setSingleLine(true);
        holder.forks.setText(repoList.get(position).getForks());

        holder.mainImageResItem.setImageResource(0);

        ImageLoader.getInstance().displayImage(repoList.get(position).getOwner().getAvatarUrl(), holder.mainImageResItem);

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {

                switch (view.getId()) {
                    case R.id.item_card:
                        Intent intent = new Intent(view.getContext(), DetailActivity.class);
                        intent.putExtra(REPOSITORY_KEY, repoList.get(position));
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

    private ArrayList<Repository> filterByName(ArrayList<Repository> list) {

        ArrayList<Repository> filteredList = new ArrayList<>();

        for (Repository elem : list) {
            if (elem.getName().toLowerCase().contains(GitHubSearch.getQuery().toLowerCase())) {
                filteredList.add(elem);
            }
        }

        return filteredList;
    }

    @Override
    public int getItemCount() {
        if (repoList != null) {
            return repoList.size();
        }
        return 0;
    }
}
