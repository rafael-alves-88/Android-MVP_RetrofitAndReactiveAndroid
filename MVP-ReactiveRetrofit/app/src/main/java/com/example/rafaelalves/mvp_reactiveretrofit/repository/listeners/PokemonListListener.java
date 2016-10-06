package com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners;

import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonList;

public interface PokemonListListener extends BaseListener {

    /**
     * PokemonList done loading
     *
     * @param pokemonList - PokemonList model
     */
    void onPokemonListLoad(PokemonList pokemonList);
}
