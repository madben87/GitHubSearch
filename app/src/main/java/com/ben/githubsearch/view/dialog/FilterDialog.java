package com.ben.githubsearch.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ben.githubsearch.GitHubSearch;
import com.ben.githubsearch.R;
import com.ben.githubsearch.util.MadLog;
import com.ben.githubsearch.view.result.ResultPresenterImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FilterDialog extends DialogFragment {

    @BindView(R.id.filter_query)
    EditText filterQuery;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private View view;
    private Unbinder unbinder;

    //@Inject
    //ResultPresenterImpl resultPresenter;

    @Inject
    public FilterDialog() {}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.dialog_filter_list, null);

        unbinder = ButterKnife.bind(this, view);

        GitHubSearch.getInjector().inject(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();

        MadLog.log(this, "onDestroyView");
    }
}
