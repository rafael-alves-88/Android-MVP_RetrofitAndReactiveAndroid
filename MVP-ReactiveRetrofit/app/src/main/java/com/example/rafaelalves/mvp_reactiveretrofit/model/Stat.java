
package com.example.rafaelalves.mvp_reactiveretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("stat")
    @Expose
    public Stat_ stat;
    @SerializedName("effort")
    @Expose
    public Integer effort;
    @SerializedName("base_stat")
    @Expose
    public Integer baseStat;

}
