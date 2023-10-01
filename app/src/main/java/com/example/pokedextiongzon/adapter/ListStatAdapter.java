package com.example.pokedextiongzon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.example.pokedextiongzon.model.PokemonStat;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListStatAdapter extends RecyclerView.Adapter<ListStatAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonStat> pokemonStatList;
    public ListStatAdapter(Context context, ArrayList<PokemonStat> arrayList) {
        this.context = context;
        this.pokemonStatList = arrayList;

    }

    @NonNull
    @Override
    public ListStatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStatAdapter.ViewHolder holder, int position) {
        holder.bind(pokemonStatList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonStatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, stat;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            stat = itemView.findViewById(R.id.pokemonName);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonStat pokemonStat){
            //url.setText(pokemonList.getUrl()+"");
            stat.setText(pokemonStat.getStatName());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
