package com.example.pokeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class PokeInfo extends Activity {
    String URL = "https://pokeapi.co/api/v2/pokemon/";


    TextView txtHeight, txtWeight, txtXP, txtName, txtType;
    ImageButton btnBack;
    ImageView imgPokemon;
    ConstraintLayout myLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poke_info);

        txtHeight = findViewById(R.id.txtHeight);
        txtWeight = findViewById(R.id.txtWeight);
        txtXP = findViewById(R.id.txtXp);
        txtName = findViewById(R.id.txtName);
        txtType = findViewById(R.id.txtType);
        imgPokemon = findViewById(R.id.imgPoke);
        myLayout = findViewById(R.id.poke_info_layout);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> closeActivity());


        Intent myIntent = getIntent();
        String search = myIntent.getStringExtra("search");
        searchPokemon(search);

    }


    public void searchPokemon(String pokemon){
        handleSearch(pokemon, new ApiCallback() {
            @Override
            public void onOkHttpResponse(Pokemon pokemon) {
                if(!pokemon.isOk()) {
                    sendErrorMessage();
                    return;
                }
                txtHeight.setText(pokemon.getHeight());
                txtName.setText(pokemon.getName());
                txtWeight.setText(pokemon.getWeight());
                txtXP.setText(pokemon.getBaseXP());
                txtType.setText(pokemon.getType());
                imgPokemon.setImageDrawable(pokemon.getSprite());
                myLayout.setBackgroundColor(pokemon.getColor());
            }

            @Override
            public void onOkHttpFailure() {
                sendErrorMessage();
            }
        });
    }



    public void handleSearch(String search, ApiCallback callback){

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL+search)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onOkHttpFailure();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String jsonStr = Objects.requireNonNull(response.body()).string();
                Pokemon pokemon = new Pokemon(jsonStr);

                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(() -> callback.onOkHttpResponse(pokemon));
            }
        });

    }


    public void sendErrorMessage(){
        Toast.makeText(this,"Pokemon not found", Toast.LENGTH_LONG).show();
        closeActivity();
    }

    public void closeActivity(){
        finish();
    }

}
