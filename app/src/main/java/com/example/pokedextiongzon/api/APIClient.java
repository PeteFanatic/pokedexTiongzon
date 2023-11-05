package com.example.pokedextiongzon.api;

import android.annotation.SuppressLint;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.model.PokemonList;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    final static String BASE_URL = "https://pokeapi.co/api/v2/";
    static Retrofit retrofit = null;
    public APIInterface apiInterface;
    private int offset = 0;
    private int limit = 100;
    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

    public static Retrofit retrofit_pokemonList(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    public APIResponse fetchPokemonData(int limit, int offset) throws IOException {
        // Make a network request using the apiInterface
        apiInterface = retrofit_pokemonList().create(APIInterface.class);
        Call<APIResponse> call = apiInterface.fetchPokemonList(limit, offset);
        try {
            retrofit2.Response<APIResponse> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }
            else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public APIResponse fetchPokemonInfo(String name){
        // Make a network request using the apiInterface
        apiInterface = retrofit_pokemonList().create(APIInterface.class);
        Call<APIResponse> call = apiInterface.fetchPokemonInfo(name);
        try {
            retrofit2.Response<APIResponse> response = call.execute();
            if(response.isSuccessful()){
                return response.body();
            }
            else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
