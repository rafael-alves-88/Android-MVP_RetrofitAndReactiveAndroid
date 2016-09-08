
package com.example.rafaelalves.mvp_reactiveretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ability {

    @SerializedName("slot")
    @Expose
    public Integer slot;
    @SerializedName("is_hidden")
    @Expose
    public Boolean isHidden;
    @SerializedName("ability")
    @Expose
    public Ability_ ability;

}
