package com.example.pokeapp;

interface ApiCallback{
    void onOkHttpResponse(Pokemon pokemon);
    void onOkHttpFailure();
}
