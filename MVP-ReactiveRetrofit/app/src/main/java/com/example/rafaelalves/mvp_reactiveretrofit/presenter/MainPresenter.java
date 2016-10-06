package com.example.rafaelalves.mvp_reactiveretrofit.presenter;

import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonList;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.api.PokemonAPI;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners.PokemonListListener;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
* Presenter for View MainActivity
*/
public class MainPresenter {

    public static final String POKEMON_FROM_LIST = "PokemonFromList";

    private PokemonAPI mPokemon;

    /**
     * Class constructor
    */
    public MainPresenter() {
        mPokemon = new PokemonAPI();
    }

    /**
     * Load Pokemon List
     *
     * @param offset - Offset for starting Pokemon at page
     * @param listener - PokemonListListener to handle Callbacks
    */
    public void loadPokemonList(int offset, final PokemonListListener listener) {
        listener.onRequestStart();

        mPokemon.getPokemonAPI()
                .getPokemonList(offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PokemonList>() {
                    @Override
                    public void onCompleted() {
                        listener.onRequestFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onRequestFinish();
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(PokemonList pokemonList) {
                        listener.onPokemonListLoad(pokemonList);
                    }
                });
    }
}
