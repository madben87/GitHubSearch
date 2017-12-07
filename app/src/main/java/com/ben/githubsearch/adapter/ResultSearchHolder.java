package com.ben.githubsearch.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ben.githubsearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultSearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_card)
    CardView itemCard;
    @BindView(R.id.repo_name)
    TextView repoName;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.forks)
    TextView forks;
    @BindView(R.id.avatar)
    ImageView mainImageResItem;
    @BindView(R.id.descr_layout)
    LinearLayout descrLayout;

    private ItemClick listener;


    public ResultSearchHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        itemCard.setPreventCornerOverlap(false);
        itemCard.setOnClickListener(this);
        descrLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        listener.onItemClick(v, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }
}
