package com.example.pokedextiongzon;

import static android.media.CamcorderProfile.get;
import static com.example.pokedextiongzon.api.APIClient.retrofit_pokemonList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonList;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListFragment extends Fragment implements ListNameAdapter.RecyclerViewClickListener{
    RecyclerView rv_pokemonList;
    ListNameAdapter listNameAdapter;
    ArrayList<PokemonList> pokemonListArrayList;
//    private ListNameAdapter.RecyclerViewClickListener listener;
    Context context = getContext();
    int limit = 100;
    int offset = 0;
    private ProgressBar progressBar;
    public PokemonListFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_pokemon, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        ExecutorService service = Executors.newSingleThreadExecutor();
        pokemonListArrayList = new ArrayList<>();
        service.execute(new Runnable(){
            @Override
            public void run(){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                });
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        if(pokemonListArrayList!=null){
                            rv_pokemonList = view.findViewById(R.id.pokemon_list);
                            mainRecyclerView(view);
                            pokemonServices();
                        }
                    }
                });
            }
        });
        return view;
    }
    public void pokemonServices() {

        //System.out.println("------------------");
        retrofit_pokemonList().create(APIInterface.class).fetchPokemonList(limit,offset)
                .enqueue(new Callback<APIResponse>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                        System.out.println("------------------");
                        if(response.code() == 200 && response.body()!=null){
                                System.out.println("------------------");
                                pokemonListArrayList.addAll(response.body().getPokemonResults());
                                listNameAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {
                        call.cancel();
                    }
                });
    }

    public void mainRecyclerView(View view){
//        System.out.println("------------------");

        rv_pokemonList = view.findViewById(R.id.pokemon_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rv_pokemonList.setLayoutManager(layoutManager);
        rv_pokemonList.setHasFixedSize(true);
        listNameAdapter = new ListNameAdapter(getContext(), pokemonListArrayList,this);
        rv_pokemonList.setAdapter(listNameAdapter);
    }

    public void onClick(View v, int position){
        Fragment fragment = PokemonDetailsFragment.newInstance(pokemonListArrayList.get(position).getName());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(requireActivity().getSupportFragmentManager().findFragmentByTag("main_fragment"));
        transaction.replace(R.id.frameLayout,fragment,"name");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}