package com.example.pokedextiongzon;

import static com.example.pokedextiongzon.api.APIClient.retrofit_pokemonList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.model.PokemonSprites;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPokemonFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    RecyclerView rv_pokemonList; //rv = RecyclerView
    ListNameAdapter listNameAdapter;
    ArrayList<PokemonList> pokemonListArrayList;
    private ListNameAdapter.RecyclerViewClickListener listener;
    Context context = getContext();
    int limit = 100;
    int offset = 0;
    public MainPokemonFragment() {
    }


    public static MainPokemonFragment newInstance(String param1, String param2) {
        MainPokemonFragment fragment = new MainPokemonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_pokemon, container, false);
        setOnClickListener();
        rv_pokemonList = view.findViewById(R.id.pokemon_list);
        rv_pokemonList.setLayoutManager(new LinearLayoutManager(getActivity()));
        listNameAdapter = new ListNameAdapter(MainPokemonFragment.this, pokemonListArrayList,listener);
        rv_pokemonList.setAdapter(listNameAdapter);
        pokemonServices();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void setOnClickListener(){
        listener = new ListNameAdapter.RecyclerViewClickListener(){
            @Override
            public void onClick(View v, int position){
                Intent intent = new Intent(getActivity(),SecondActivity.class);
                intent.putExtra("name",pokemonListArrayList.get(position).getName());
                intent.putExtra("url",pokemonListArrayList.get(position).getUrl());
                startActivity(intent);
            }
        };
    }
    public void pokemonServices() {
        pokemonListArrayList = new ArrayList<>();

        mainRecyclerView();

        retrofit_pokemonList().create(APIInterface.class).fetchPokemonList(limit,offset)
                .enqueue(new Callback<APIResponse>() {

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        if(response.code() == 200){
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
    public void mainRecyclerView(){
        rv_pokemonList = findViewById(R.id.pokemon_list);
        rv_pokemonList.setLayoutManager(new LinearLayoutManager(getActivity()));
        listNameAdapter = new ListNameAdapter(MainPokemonFragment.this, pokemonListArrayList,listener);
        rv_pokemonList.setAdapter(listNameAdapter);
    }
}