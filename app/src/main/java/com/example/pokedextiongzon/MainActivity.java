package com.example.pokedextiongzon;

//import static com.example.pokedextiongzon.client.APIClient.retrofit;
import static com.example.pokedextiongzon.api.APIClient.retrofit_pokemonList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.model.PokemonSprites;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_pokemonList; //rv = RecyclerView
    ListNameAdapter listNameAdapter;
    ArrayList<PokemonList> pokemonListArrayList;
    ArrayList<PokemonSprites> pokemonSpritesArrayList;
    int limit = 100;
    int offset = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonListArrayList = new ArrayList<>();
        pokemonSpritesArrayList = new ArrayList<>();

        rv_pokemonList = findViewById(R.id.pokemon_list);
        rv_pokemonList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listNameAdapter = new ListNameAdapter(MainActivity.this, pokemonListArrayList);
        rv_pokemonList.setAdapter(listNameAdapter);

        pokemonServices();
    }

    public void pokemonServices() {

        retrofit_pokemonList().create(APIInterface.class).fetchPokemonList(limit,offset)
                .enqueue(new Callback<APIResponse>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code() == 200){
                    System.out.println("----------------------------");
                    if(response.body()!=null){


                        pokemonListArrayList.addAll(response.body().getPokemonResults());
                        listNameAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                call.cancel();
            }
        });
    }
}

