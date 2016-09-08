package com.example.rafaelalves.mvp_reactiveretrofit.presenter;

import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonList;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.api.PokemonAPI;
import com.example.rafaelalves.mvp_reactiveretrofit.view.ui.MainActivity;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
* Presenter for View MainActivity
*/
public class MainPresenter {

    public static final String POKEMON_FROM_LIST = "PokemonFromList";

    private MainActivity mView;
    private PokemonAPI mPokemon;

    /*
    * Class constructor
    *
    * @param activity MainActivity
    */
    public MainPresenter(MainActivity activity) {
        mView = activity;
        mPokemon = new PokemonAPI();
    }

    /*
    * Load Pokemon List
    *
    * @param offset Offset for starting Pokemon at page
    */
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
}
