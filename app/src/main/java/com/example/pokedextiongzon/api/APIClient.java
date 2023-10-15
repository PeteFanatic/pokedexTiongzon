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
    APIInterface apiInterface;
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

    public APIResponse fetchPokemonData() throws IOException {
        // Make a network request using the apiInterface
        apiInterface = retrofit_pokemonList().create(APIInterface.class);
        Call<APIResponse> call = apiInterface.fetchPokemonList(limit,offset);
        ArrayList<PokemonList> pokemonListArrayList = new ArrayList<>();

        // Execute the call and return the response
        retrofit_pokemonList().create(APIInterface.class).fetchPokemonList(limit,offset)
                .enqueue(new Callback<APIResponse>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        ListNameAdapter listNameAdapter;
                        System.out.println("------------------");
                        if(response.code() == 200 && response.body()!=null){
                            System.out.println("------------------");
                            pokemonListArrayList.get(1);
//                            pokemonListArrayList.addAll(response.body().getPokemonResults());
                            //listNameAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        call.cancel();
                    }
                });
        return call.execute().body();
//        Call<APIResponse> call = apiInterface.fetchPokemonList(limit, offset);
//        try {
//            return call.execute().body();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
    }

}
