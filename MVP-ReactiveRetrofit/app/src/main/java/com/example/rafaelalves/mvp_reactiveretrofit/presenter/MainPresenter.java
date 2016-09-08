package com.example.rafaelalves.mvp_reactiveretrofit.presenter;

import android.content.Intent;

import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonList;
import com.example.rafaelalves.mvp_reactiveretrofit.model.Result;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.api.PokemonAPI;
import com.example.rafaelalves.mvp_reactiveretrofit.view.ui.DetailsActivity;
import com.example.rafaelalves.mvp_reactiveretrofit.view.ui.MainActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {

    public static final String POKEMON_FROM_LIST = "PokemonFromList";

    private MainActivity mView;
    private PokemonAPI mPokemon;

    public MainPresenter(MainActivity activity) {
        mView = activity;
        mPokemon = new PokemonAPI();
    }

    public void loadPokemonList(int offset) {
        mView.showLoading();

        mPokemon.getPokemonAPI()
                .getPokemonList(offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PokemonList>() {
                    @Override
                    public void onCompleted() {
                        mView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PokemonList pokemonList) {
                        mView.displayPokemonList(pokemonList.results);
                    }
                });
    }

    public void startDetailsActivity(Result pokemonFromList) {
        Intent intent = new Intent(mView, DetailsActivity.class);
        intent.putExtra(POKEMON_FROM_LIST, pokemonFromList);
        mView.startActivity(intent);
    }
}
