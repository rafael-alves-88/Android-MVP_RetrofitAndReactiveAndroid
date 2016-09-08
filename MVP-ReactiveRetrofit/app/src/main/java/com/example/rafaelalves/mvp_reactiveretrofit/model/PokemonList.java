
package com.example.rafaelalves.mvp_reactiveretrofit.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonList {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("previous")
    @Expose
    public String previous;
    @SerializedName("results")
    @Expose
    public List<Result> results = new ArrayList<Result>();
    @SerializedName("next")
    @Expose
    public String next;

}
