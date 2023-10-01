package com.example.pokedextiongzon;

//import static com.example.pokedextiongzon.api.APIClient.retrofit;
import static com.example.pokedextiongzon.api.APIClient.retrofit_pokemonList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.api.APIClient;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.model.PokemonSprites;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info);
        TextView nameText = findViewById(R.id.pokemonName);
        Bundle extras = getIntent().getExtras();
        String pokeName = "Name not set";
        if(extras!=null){
            pokeName=extras.getString("name");
        }
        nameText.setText(pokeName);
        pokemonServices(pokeName);
    }
    public void pokemonServices(String pokeName) {

        retrofit_pokemonList().create(APIInterface.class).fetchPokemonInfo(pokeName)
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
//        APIInterface apiInterface = retrofit.create(apiInterface.class);
//
//        Call<APIResponse> fetchPokemonInfo = apiInterface.getName();
    }
}
