package com.example.pokedextiongzon.api;

import com.example.pokedextiongzon.model.PokemonListId;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("pokemon/")
    Call<APIResponse> fetchPokemonList(@Query("limit") int limit,
                                       @Query("offset") int offset);
    @GET("pokemon/{name}")
    Call<APIResponse> fetchPokemonInfo(@Path("name") String name);


}
