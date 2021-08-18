package com.example.pokeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText searchText;
    ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.txtSearch);
        searchButton = findViewById(R.id.btnSearch);

        searchButton.setOnClickListener(view -> {
            search();
        });
    }

    private void search() {
        String search = searchText.getText().toString();
        searchText.setText("");
        Intent it = new Intent(MainActivity.this, PokeInfo.class);
        it.putExtra("search", search);
        MainActivity.this.startActivity(it);
    }


}