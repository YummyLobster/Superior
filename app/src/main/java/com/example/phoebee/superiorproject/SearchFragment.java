package com.example.phoebee.superiorproject;



import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.phoebee.superiorproject.model.Markets;
import com.example.phoebee.superiorproject.utilities.JSONUtils;
import com.example.phoebee.superiorproject.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private EditText mSearchBoxEditText;

    private ProgressBar mProgressBar;
    private MarketsAdapter mAdapter;
    private RecyclerView mGoodList;

    private CheckBox mCbRanch99;
    private CheckBox mCbHmart;


    private ImageButton mBtnSearch;
    private String hmart="0", ranch99="0";


    //display all products
    private ArrayList<Markets> markets = new ArrayList<>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Search");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.nav_search, container, false);

        //ImageView mFrontImage = (ImageView) view.findViewById(R.id.frontImage);
        //mFrontImage.setVisibility(View.GONE);



        mSearchBoxEditText = (EditText) view.findViewById(R.id.et_search_box);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        mGoodList = (RecyclerView)view.findViewById(R.id.rv_numbers);

        //initiate a checkbox
        mCbRanch99 = (CheckBox) view.findViewById(R.id.checkbox_ranch99);
        mCbHmart = (CheckBox) view.findViewById(R.id.checkbox_hmart);


        mCbRanch99.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)ranch99="1";
                else ranch99="0";
            }
        });


        mCbHmart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)hmart="1";
                else hmart="0";
            }
        });


        mBtnSearch = (ImageButton) view.findViewById(R.id.btn_search);

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL url = makeGithubSearchQuery();
                MarketQueryTask task = new MarketQueryTask();
                task.execute(url);
            }
        });


        //mGoodList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mGoodList.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //mGoodList.setHasFixedSize(true);

        mAdapter = new MarketsAdapter(this, markets);
        mGoodList.setAdapter(mAdapter);


        return view;
    }

    private URL makeGithubSearchQuery() {
        String githubQuery = mSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery, ranch99, hmart);
        String urlString = githubSearchUrl.toString();
        Log.d("myURL", urlString);
        return githubSearchUrl;
    }


    class MarketQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            String githubSearchResults = "";
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }

        @Override
        protected void onPostExecute(String s) {
            //Log.d("myJSON", s);
            super.onPostExecute(s);
            mAdapter.mMarkets.clear();
            mProgressBar.setVisibility(View.GONE);
            markets = JSONUtils.makeRepositoryList(s);
            mAdapter.mMarkets.addAll(markets);
            mAdapter.notifyDataSetChanged();
        }
    }


}
