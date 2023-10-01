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

import com.example.pokedextiongzon.model.PokemonStat;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListBaseStatAdapter extends RecyclerView.Adapter<ListBaseStatAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonStat> pokemonBaseStatList;
    public ListBaseStatAdapter(Context context, ArrayList<PokemonStat> arrayList) {
        this.context = context;
        this.pokemonBaseStatList = arrayList;

    }

    @NonNull
    @Override
    public ListBaseStatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListBaseStatAdapter.ViewHolder holder, int position) {
        holder.bind(pokemonBaseStatList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonBaseStatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, base_stat;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            base_stat = itemView.findViewById(R.id.pokemonName);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonStat pokemonStat){
            //url.setText(pokemonList.getUrl()+"");
            base_stat.setText(pokemonStat.getBase_stat());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
