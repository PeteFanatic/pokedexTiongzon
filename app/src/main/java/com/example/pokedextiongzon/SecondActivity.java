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
import com.example.pokedextiongzon.model.PokemonListId;
import com.example.pokedextiongzon.model.PokemonSprites;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondActivity extends AppCompatActivity {
    TextView nameText,idText;
    PokemonListId pokemonListId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info);
        nameText = findViewById(R.id.pokemonName);
        idText = findViewById(R.id.pokemonId);

        Bundle extras = getIntent().getExtras();
        String pokeName = "Name not set";
        if(extras!=null){
            pokeName=extras.getString("name");
        }
        nameText.setText(pokeName);
        displayId(pokeName);
    }
    public void displayId (String pokeName){
        System.out.println(pokeName);
        APIInterface apiInterface = retrofit_pokemonList().create(APIInterface.class);
        Call<PokemonListId> call = apiInterface.fetchPokemonInfo2(pokeName);
        call.enqueue(new Callback<PokemonListId>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PokemonListId> call, Response<PokemonListId> response) {
                if(!response.isSuccessful()){
                    idText.setText("Code" + response.code());
                    return;
                }
                int resource = response.body().getId();
                //int id = PokemonListId.getId();

                    //String content = "";
                System.out.println(resource);

                    //content += pokemonListId.getId();
                String content = String.valueOf(PokemonListId.getId());
                    //idText.append(resource);

            }

            @Override
            public void onFailure(Call<PokemonListId> call, Throwable t) {
                idText.setText(t.getMessage());
            }
        });

    }
}
