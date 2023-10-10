//package com.example.pokedextiongzon;
//
////import static com.example.pokedextiongzon.api.APIClient.retrofit;
//
//import static com.example.pokedextiongzon.api.APIClient.retrofit_pokemonList;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.pokedextiongzon.adapter.ListAbilityAdapter;
//import com.example.pokedextiongzon.adapter.ListStatAdapter;
//import com.example.pokedextiongzon.adapter.ListTypeAdapter;
//import com.example.pokedextiongzon.api.APIInterface;
//import com.example.pokedextiongzon.api.APIResponse;
//import com.example.pokedextiongzon.model.PokemonAbilities;
//import com.example.pokedextiongzon.model.PokemonStats;
//import com.example.pokedextiongzon.model.PokemonTypes;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//
//public class SecondActivity extends AppCompatActivity {
//    TextView nameText,idText;
//    RecyclerView rv_pokemonType, rv_pokemonStat, rv_pokemonAbilities; //rv = RecyclerView
//    ListTypeAdapter listTypeAdapter;
//    ListAbilityAdapter listAbilityAdapter;
//    ArrayList<PokemonTypes> pokemonListArrayType;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pokemon_info);
//        nameText = findViewById(R.id.pokemonName);
//        idText = findViewById(R.id.pokemonId);
//
//        Bundle extras = getIntent().getExtras();
//        String pokeName = "Name not set";
//        if(extras!=null){
//            pokeName=extras.getString("name");
//        }
//        nameText.setText(pokeName);
//        System.out.println(pokeName);
//        //displayId(pokeName);
//        displayType(pokeName);
//    }
//    public void displayType(String pokeName){
//        pokemonListArrayType = new ArrayList<>();
//
//
//        Retrofit retrofit = retrofit_pokemonList(); // Create your Retrofit instance
//        APIInterface apiInterface = retrofit.create(APIInterface.class);
//
//
//// Assuming 'pokeName' is the name of the Pokémon you want to fetch
//        Call<APIResponse> call = apiInterface.fetchPokemonInfo(pokeName);
//        call.enqueue(new Callback<APIResponse>(){
//
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
//                if(response.code() == 200 && response.body()!=null){
//
//                    APIResponse apiResponse = response.body();
//
//                    pokemonType_rv(apiResponse);
//                    pokemonStat_rv(apiResponse);
//                    abilities_rv(apiResponse);
////                    pokemonListArrayType.addAll(response.body().getTypes());
////                    listTypeAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<APIResponse> call, Throwable t) {
//                t.printStackTrace();
//            }
//        });
//    }
//    public void pokemonType_rv(APIResponse apiResponse){
//        ArrayList<PokemonTypes> types = apiResponse.getTypes();
//        Collections.sort(types, new Comparator<PokemonTypes>() {
//            @Override
//            public int compare(PokemonTypes type1, PokemonTypes type2) {
//                // Compare by slot
//                return Integer.compare(type1.getSlot(), type2.getSlot());
//            }
//        });
//        rv_pokemonType = findViewById(R.id.rvType);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rv_pokemonType.setLayoutManager(layoutManager);
//        ListTypeAdapter adapter = new ListTypeAdapter(SecondActivity.this,types);
//        rv_pokemonType.setAdapter(adapter);
//
//    }
//    public void pokemonStat_rv(APIResponse apiResponse){
//        ArrayList<PokemonStats> stats = apiResponse.getStats();
//        rv_pokemonStat = findViewById(R.id.rvStat);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rv_pokemonStat.setLayoutManager(layoutManager);
//        ListStatAdapter adapter = new ListStatAdapter(SecondActivity.this,stats);
//        rv_pokemonStat.setAdapter(adapter);
//    }
//    public void abilities_rv(APIResponse apiResponse){
//        ArrayList<PokemonAbilities> abilities = apiResponse.getAbilities();
//
//        rv_pokemonAbilities = findViewById(R.id.rvAbilities);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rv_pokemonAbilities.setLayoutManager(layoutManager);
//        ListAbilityAdapter adapter = new ListAbilityAdapter(SecondActivity.this,abilities);
//        rv_pokemonAbilities.setAdapter(adapter);
//    }
//
//}
