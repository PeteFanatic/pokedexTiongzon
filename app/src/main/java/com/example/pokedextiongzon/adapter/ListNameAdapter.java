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

import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListNameAdapter extends RecyclerView.Adapter<ListNameAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonList> pokemonListName;
    public ListNameAdapter(Context context, ArrayList<PokemonList> arrayList) {
        this.context = context;
        this.pokemonListName = arrayList;

    }

    @NonNull
    @Override
    public ListNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNameAdapter.ViewHolder holder, int position) {
        holder.bind(pokemonListName.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonListName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, name;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            name = itemView.findViewById(R.id.pokemonName);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonList pokemonList){
            //url.setText(pokemonList.getUrl()+"");
            name.setText(pokemonList.getName());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
