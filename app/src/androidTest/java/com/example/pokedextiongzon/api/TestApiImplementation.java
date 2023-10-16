package com.example.pokedextiongzon.api;

import static com.example.pokedextiongzon.api.APIClient.interceptor;
import static com.example.pokedextiongzon.api.APIClient.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestApiImplementation {
    final static String BASE_URL = "https://pokeapi.co/api/v2/";
    static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    public static Retrofit provideApi(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(retrofit.getClass());
        }

        return retrofit;
    }
}
