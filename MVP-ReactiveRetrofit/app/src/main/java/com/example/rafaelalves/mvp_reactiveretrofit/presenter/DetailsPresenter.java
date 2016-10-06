package com.example.rafaelalves.mvp_reactiveretrofit.presenter;

import com.example.rafaelalves.mvp_reactiveretrofit.model.Pokemon;
import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonForm;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.api.PokemonAPI;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners.PokemonDetailsListener;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners.PokemonSpriteListener;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
* Presenter for View DetailsActivity
*/
public class DetailsPresenter {

    private PokemonAPI mPokemon;

    /**
     * Class constructor
    */
    public DetailsPresenter() {
        mPokemon = new PokemonAPI();
    }

    /**
     * Loads Pokemon Details and Sprites
     *
     * @param pokemonName - Pokemon name to get details
     * @param listener - PokemonDetailsListener to handle Callbacks
    */
    public void loadPokemonDetails(String pokemonName, final PokemonDetailsListener listener) {
        listener.onRequestStart();

        // Loads Details
        mPokemon.getPokemonAPI()
                .getPokemon(pokemonName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pokemon>() {
                    @Override
                    public void onCompleted() {
                        listener.onRequestFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                        listener.onRequestFinish();
                    }

                    @Override
                    public void onNext(Pokemon pokemon) {
                        listener.onPokemonDetailsLoad(pokemon);
                    }
                });
    }

    /**
     * Loads Pokemon Sprite
     *
     * @param pokemonName - Pokemon name to get sprite
     * @param listener - PokemonFormListener to handle Callbacks
     */
    public void loadPokemonSprite(String pokemonName, final PokemonSpriteListener listener) {
        // Loads Sprite
        mPokemon.getPokemonAPI()
                .getPokemonForm(pokemonName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PokemonForm>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onNext(PokemonForm pokemonForm) {
                        listener.onPokemonFormLoad(pokemonForm.sprites.frontDefault);
                    }
                });
    }
}
