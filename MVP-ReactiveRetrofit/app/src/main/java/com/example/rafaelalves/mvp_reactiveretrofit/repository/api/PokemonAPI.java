package com.example.rafaelalves.mvp_reactiveretrofit.repository.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonAPI {

    private static final String SERVER_URL = "http://pokeapi.co/api/v2/";
    IPokemonAPI mPokemonAPI;

    /**
    * Retrofit Constructor for API calls
    */
    public PokemonAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_URL)
                .build();

        mPokemonAPI = retrofit.create(IPokemonAPI.class);
    }

    public IPokemonAPI getPokemonAPI() {
        return mPokemonAPI;
    }
}
