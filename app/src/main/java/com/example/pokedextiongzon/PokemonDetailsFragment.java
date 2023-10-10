package com.example.pokedextiongzon;

import static com.example.pokedextiongzon.api.APIClient.retrofit_pokemonList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pokedextiongzon.adapter.ListAbilityAdapter;
import com.example.pokedextiongzon.adapter.ListStatAdapter;
import com.example.pokedextiongzon.adapter.ListTypeAdapter;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonAbilities;
import com.example.pokedextiongzon.model.PokemonStats;
import com.example.pokedextiongzon.model.PokemonTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PokemonDetailsFragment extends Fragment {
    TextView idText;
    ImageView imageView;
    RecyclerView rv_pokemonType, rv_pokemonStat, rv_pokemonAbilities;
    ArrayList<PokemonTypes> pokemonListArrayType;
    View view;
    private ProgressBar progressBar;
    private String getPokeName;
    public PokemonDetailsFragment() {
        // Required empty public constructor
    }
    public static PokemonDetailsFragment newInstance(String pokeName){
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name",pokeName);
        fragment.setArguments(bundle);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments()!= null){
            getPokeName = getArguments().getString("name");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pokemon_info, container, false);
        TextView pokeName = view.findViewById(R.id.pokemonName);
        progressBar = view.findViewById(R.id.progressBar2);
        ExecutorService service = Executors.newSingleThreadExecutor();
        Bundle bundle = getArguments();
        service.execute(new Runnable(){
            @Override
            public void run(){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
                if(bundle!=null){
                    try{
                        Thread.sleep(800);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(bundle!=null){
                            progressBar.setVisibility(View.GONE);
                            String receiveName = bundle.getString("name");
                            pokeName.setText(receiveName);
                            System.out.println(receiveName);
                            displayDetails(receiveName);
                            //displayType(receiveName);
                        }
                    }
                });
            }
        });
        return view;
    }
    public void displayDetails(String pokeName){
        pokemonListArrayType = new ArrayList<>();
        Retrofit retrofit = retrofit_pokemonList();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<APIResponse> call = apiInterface.fetchPokemonInfo(pokeName);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if(response.code() == 200 && response.body()!=null){

                    APIResponse apiResponse = response.body();
                    idText = getView().findViewById(R.id.pokemonDetail_Id);
                    imageView = getView().findViewById(R.id.pokemonImg);
                    String getId = String.format("#%03d",apiResponse.getId());
                    String imageUrl = apiResponse.getSprites().getFront_default();
                    idText.setText(getId+"");
                    Glide.with(getActivity()).load(imageUrl).into(imageView);
                    pokemonType_rv(apiResponse);
                    pokemonStat_rv(apiResponse);
                    abilities_rv(apiResponse);
//                    pokemonListArrayType.addAll(response.body().getTypes());
//                    listTypeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

            }
        });
    }
    public void pokemonType_rv(APIResponse apiResponse){
        ArrayList<PokemonTypes> types = apiResponse.getTypes();
        Collections.sort(types, new Comparator<PokemonTypes>() {
            @Override
            public int compare(PokemonTypes type1, PokemonTypes type2) {
                // Compare by slot
                return Integer.compare(type1.getSlot(), type2.getSlot());
            }
        });
        rv_pokemonType = getView().findViewById(R.id.rvType);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_pokemonType.setLayoutManager(layoutManager);
        ListTypeAdapter adapter = new ListTypeAdapter(getContext(),types);
        rv_pokemonType.setAdapter(adapter);

    }
    public void pokemonStat_rv(APIResponse apiResponse){
        ArrayList<PokemonStats> stats = apiResponse.getStats();
        rv_pokemonStat = getView().findViewById(R.id.rvStat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_pokemonStat.setLayoutManager(layoutManager);
        ListStatAdapter adapter = new ListStatAdapter(getContext(),stats);
        rv_pokemonStat.setAdapter(adapter);
    }
    public void abilities_rv(APIResponse apiResponse){
        ArrayList<PokemonAbilities> abilities = apiResponse.getAbilities();

        rv_pokemonAbilities = getView().findViewById(R.id.rvAbilities);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv_pokemonAbilities.setLayoutManager(layoutManager);
        ListAbilityAdapter adapter = new ListAbilityAdapter(getContext(),abilities);
        rv_pokemonAbilities.setAdapter(adapter);
    }
}