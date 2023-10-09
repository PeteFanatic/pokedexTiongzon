package com.example.pokedextiongzon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PokemonDetailsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    View view;
    public PokemonDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pokemon_info, container, false);
        TextView pokeName = view.findViewById(R.id.pokemonName);
        Bundle bundle = getArguments();
        if(bundle!=null){
            String receiveName = bundle.getString("name");
            pokeName.setText(receiveName);
            System.out.println(receiveName);
            //displayType(receiveName);
        }

        return view;
    }
    public static PokemonDetailsFragment newInstance(String param1){
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
}