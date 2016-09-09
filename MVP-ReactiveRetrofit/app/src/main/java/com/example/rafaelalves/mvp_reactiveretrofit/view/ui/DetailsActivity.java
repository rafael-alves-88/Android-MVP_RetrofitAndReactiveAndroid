package com.example.rafaelalves.mvp_reactiveretrofit.view.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaelalves.mvp_reactiveretrofit.R;
import com.example.rafaelalves.mvp_reactiveretrofit.model.Pokemon;
import com.example.rafaelalves.mvp_reactiveretrofit.model.Result;
import com.example.rafaelalves.mvp_reactiveretrofit.presenter.DetailsPresenter;
import com.example.rafaelalves.mvp_reactiveretrofit.presenter.MainPresenter;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.tvName) TextView tvName;
    @Bind(R.id.tvWeight) TextView tvWeight;
    @Bind(R.id.rlLoading) RelativeLayout rlLoading;
    @Bind(R.id.ivSprite) ImageView ivSprite;
    private DetailsPresenter mDetailsPresenter;

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
            mDetailsPresenter = new DetailsPresenter(this);
            mDetailsPresenter.loadPokemonDetails(bundle);
        }
    }

    /**
    * Set Pokemon Details to View
    *
    * @param Pokemon - Object from API
    */
    public void setPokemonDetails(Pokemon pokemon) {
        tvName.setText(pokemon.name);
        tvWeight.setText(pokemon.weight.toString());
    }

    /**
    * Set Pokemon Sprite to View
    *
    * @param spriteURL - Sprite URL from API
    */
    public void setPokemonSprite(String spriteURL) {
        Picasso.with(this)
                .load(spriteURL)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.error_image)
                .into(ivSprite);
    }

    /**
    * Set Pokemon Sprite Error to View, if Retrofit cannot read from API
    */
    public void setPokemonSpriteError() {
        Picasso.with(this)
                .load(R.drawable.error_image)
                .into(ivSprite);
    }

    public void showLoading() {
        rlLoading.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        rlLoading.setVisibility(View.GONE);
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
}
