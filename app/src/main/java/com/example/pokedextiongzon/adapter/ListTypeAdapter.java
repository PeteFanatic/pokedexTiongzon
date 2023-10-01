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

import com.example.pokedextiongzon.model.PokemonType;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListTypeAdapter extends RecyclerView.Adapter<ListTypeAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonType> pokemonType;
    public ListTypeAdapter(Context context, ArrayList<PokemonType> arrayList) {
        this.context = context;
        this.pokemonType = arrayList;

    }

    @NonNull
    @Override
    public ListTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_info,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTypeAdapter.ViewHolder holder, int position) {
        holder.bind(pokemonType.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, type;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            type = itemView.findViewById(R.id.pokemonName);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonType pokemonType){
            //url.setText(PokemonType.getUrl()+"");
            type.setText(pokemonType.getType());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
