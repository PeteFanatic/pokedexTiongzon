package com.example.pokedextiongzon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;
import com.example.pokedextiongzon.model.PokemonSprites;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonSprites> pokemonSpritesImg;
    public ListImageAdapter(Context context, ArrayList<PokemonSprites> arrayList) {
        this.context = context;
        this.pokemonSpritesImg = arrayList;

    }

    @NonNull
    @Override
    public ListImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageAdapter.ViewHolder holder, int position) {
        holder.bind(pokemonSpritesImg.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonSpritesImg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //private TextView url, name;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            //name = itemView.findViewById(R.id.pokemonName);
            image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonSprites pokemonSprites){
            //url.setText(pokemonList.getUrl()+"");
            //name.setText(pokemonSprites.getName());
            Glide.with(context).load(pokemonSprites.getFront_default()).into(image);
        }
    }
}
