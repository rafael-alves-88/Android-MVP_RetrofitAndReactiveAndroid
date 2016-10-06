package com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners;

public interface PokemonSpriteListener extends BaseListener {

    /**
     * PokemonForm done loading
     */
    void onPokemonFormLoad(String sprite);
}
