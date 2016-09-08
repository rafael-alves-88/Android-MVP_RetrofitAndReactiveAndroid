
package com.example.rafaelalves.mvp_reactiveretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sprites {

    @SerializedName("front_default")
    @Expose
    public String frontDefault;
    @SerializedName("back_default")
    @Expose
    public String backDefault;
    @SerializedName("front_shiny")
    @Expose
    public String frontShiny;
    @SerializedName("back_shiny")
    @Expose
    public String backShiny;

}
