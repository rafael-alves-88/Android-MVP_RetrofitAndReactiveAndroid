package com.example.rafaelalves.mvp_reactiveretrofit.view.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rafaelalves.mvp_reactiveretrofit.R;
import com.example.rafaelalves.mvp_reactiveretrofit.model.Result;
import com.example.rafaelalves.mvp_reactiveretrofit.presenter.MainPresenter;
import com.example.rafaelalves.mvp_reactiveretrofit.view.adapters.PokemonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.rvPokemonList) RecyclerView rvPokemonList;
    @Bind(R.id.rlLoading) RelativeLayout rlLoading;
    private PokemonAdapter mPokemonAdapter;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvPokemonList.setLayoutManager(linearLayoutManager);

        // Adapter
        List<Result> dummyResult = new ArrayList<>();
        mPokemonAdapter = new PokemonAdapter(dummyResult, new PokemonAdapter.OnPokemonClickListener() {
            @Override
            public void onPokemonClick(Result pokemonFromList) {
                mMainPresenter.startDetailsActivity(pokemonFromList);
            }
        });

        rvPokemonList.setAdapter(mPokemonAdapter);

        // Presenter
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.loadPokemonList(0);
    }

    public void displayPokemonList(List<Result> pokemonLists) {
        mPokemonAdapter.clear();
        mPokemonAdapter.addAll(pokemonLists);
        mPokemonAdapter.notifyDataSetChanged();
    }

    public void showLoading() {
        rlLoading.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
