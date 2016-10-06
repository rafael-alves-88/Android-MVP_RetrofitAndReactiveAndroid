package com.example.rafaelalves.mvp_reactiveretrofit.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rafaelalves.mvp_reactiveretrofit.R;
import com.example.rafaelalves.mvp_reactiveretrofit.model.Pokemon;
import com.example.rafaelalves.mvp_reactiveretrofit.model.Result;
import com.example.rafaelalves.mvp_reactiveretrofit.presenter.DetailsPresenter;
import com.example.rafaelalves.mvp_reactiveretrofit.presenter.MainPresenter;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners.PokemonDetailsListener;
import com.example.rafaelalves.mvp_reactiveretrofit.repository.listeners.PokemonSpriteListener;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    //region [ ButterKnife ]
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tvName) TextView tvName;
    @Bind(R.id.tvWeight) TextView tvWeight;
    @Bind(R.id.rlLoading) RelativeLayout rlLoading;
    @Bind(R.id.ivSprite) ImageView ivSprite;
    //endregion

    //region [ Private Members ]
    private final String TAG = DetailsActivity.class.getSimpleName();
    private DetailsPresenter mDetailsPresenter;
    //endregion

    //region [ LifeCycle ]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Result pokemonFromList = (Result) bundle.getSerializable(MainPresenter.POKEMON_FROM_LIST);
            if (pokemonFromList != null) {
                mDetailsPresenter = new DetailsPresenter();
                getPokemonDetails(pokemonFromList.name);
                getPokemonSprite(pokemonFromList.name);
            } else {
                Log.e(TAG, "null Pokemon from list");
                this.finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region [ Private Methods ]

    /**
     * Gets Pokemon details
     *
     * @param pokemonName - Pokemon Name to get details
     */
    private void getPokemonDetails(String pokemonName) {
        mDetailsPresenter.loadPokemonDetails(pokemonName, new PokemonDetailsListener() {
            @Override
            public void onPokemonDetailsLoad(Pokemon pokemon) {
                setPokemonDetails(pokemon);
            }

            @Override
            public void onRequestStart() {
                showLoading();
            }

            @Override
            public void onRequestFinish() {
                hideLoading();
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
                hideLoading();
            }
        });
    }

    /**
     * Gets Pokemon sprite
     *
     * @param pokemonName - Pokemon Name to get details
     */
    private void getPokemonSprite(String pokemonName) {
        mDetailsPresenter.loadPokemonSprite(pokemonName, new PokemonSpriteListener() {
            @Override
            public void onPokemonFormLoad(String sprite) {
                setPokemonSprite(sprite);
            }

            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestFinish() {

            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
                setPokemonSpriteError();
            }
        });
    }

    /**
    * Set Pokemon Details to View
    *
    * @param pokemon - Object from API
    */
    private void setPokemonDetails(Pokemon pokemon) {
        tvName.setText(pokemon.name);
        tvWeight.setText(pokemon.weight.toString());
    }

    /**
    * Set Pokemon Sprite to View
    *
    * @param spriteURL - Sprite URL from API
    */
    private void setPokemonSprite(String spriteURL) {
        Picasso.with(this)
                .load(spriteURL)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error_image)
                .into(ivSprite);
    }

    /**
    * Set Pokemon Sprite Error to View, if Retrofit cannot read from API
    */
    private void setPokemonSpriteError() {
        Picasso.with(this)
                .load(R.drawable.error_image)
                .into(ivSprite);
    }

    private void showLoading() {
        rlLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }
    //endregion
}
