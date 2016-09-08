package com.example.rafaelalves.mvp_reactiveretrofit.repository.api;

import com.example.rafaelalves.mvp_reactiveretrofit.model.Pokemon;
import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonForm;
import com.example.rafaelalves.mvp_reactiveretrofit.model.PokemonList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface IPokemonAPI {

    @GET("pokemon")
    Observable<PokemonList> getPokemonList(@Query("offset") int offset);

    @GET("pokemon/{name}")
    Observable<Pokemon> getPokemon(@Path("name") String pokemonName);

    @GET("pokemon-form/{name}")
    Observable<PokemonForm> getPokemonForm(@Path("name") String pokemonName);
}
