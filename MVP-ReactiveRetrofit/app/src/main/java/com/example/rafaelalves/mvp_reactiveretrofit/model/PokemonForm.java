
package com.example.rafaelalves.mvp_reactiveretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonForm {

    @SerializedName("sprites")
    @Expose
    public Sprites sprites;
}
