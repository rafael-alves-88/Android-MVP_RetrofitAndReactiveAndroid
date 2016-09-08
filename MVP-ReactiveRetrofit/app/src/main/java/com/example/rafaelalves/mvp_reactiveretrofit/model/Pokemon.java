
package com.example.rafaelalves.mvp_reactiveretrofit.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("forms")
    @Expose
    public List<Form> forms = new ArrayList<Form>();
    @SerializedName("abilities")
    @Expose
    public List<Ability> abilities = new ArrayList<Ability>();
    @SerializedName("stats")
    @Expose
    public List<Stat> stats = new ArrayList<Stat>();
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("weight")
    @Expose
    public Integer weight;

}
