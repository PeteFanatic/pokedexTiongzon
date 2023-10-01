package com.example.pokedextiongzon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.example.pokedextiongzon.model.PokemonAbilities;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListAbilityNameAdapter extends RecyclerView.Adapter<ListAbilityNameAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonAbilities> pokemonListAbilityName;
    public ListAbilityNameAdapter(Context context, ArrayList<PokemonAbilities> arrayList) {
        this.context = context;
        this.pokemonListAbilityName = arrayList;

    }

    @NonNull
    @Override
    public ListAbilityNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAbilityNameAdapter.ViewHolder holder, int position) {
        holder.bind(pokemonListAbilityName.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonListAbilityName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, ability_name;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            ability_name = itemView.findViewById(R.id.pokemonName);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonAbilities pokemonAbilities){
            //url.setText(pokemonList.getUrl()+"");
            ability_name.setText(pokemonAbilities.getAbilityName());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
