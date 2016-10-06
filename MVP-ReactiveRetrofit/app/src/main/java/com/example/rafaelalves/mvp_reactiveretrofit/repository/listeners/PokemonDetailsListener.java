package com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners;

import com.example.rafaelalves.mvp_reactiveretrofit.model.Pokemon;

public interface PokemonDetailsListener extends BaseListener {

    /**
     * Pokemon Details done loading
     *
     * @param pokemon - Pokemon model
     */
    void onPokemonDetailsLoad(Pokemon pokemon);
}
