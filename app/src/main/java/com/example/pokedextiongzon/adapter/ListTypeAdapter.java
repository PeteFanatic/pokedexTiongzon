package com.example.pokedextiongzon.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.example.pokedextiongzon.R;
import com.example.pokedextiongzon.model.PokemonTypeColor;
import com.example.pokedextiongzon.model.PokemonTypes;

import java.util.ArrayList;

public class ListTypeAdapter extends RecyclerView.Adapter<ListTypeAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonTypes> pokemonType;
    private final PokemonTypeColor pokemonTypeColor = new PokemonTypeColor();
    public ListTypeAdapter(Context context, ArrayList<PokemonTypes> arrayList) {
        this.context = context;
        this.pokemonType = arrayList;

    }



    @NonNull
    @Override
    public ListTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.type_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTypeAdapter.ViewHolder holder, int position) {
        String selectedType = pokemonType.get(position).getType().getName();
        holder.typeText.setText(selectedType);
        int colorResourceId = pokemonTypeColor.getTypeColor(selectedType);
        int typeColor = ContextCompat.getColor(context,colorResourceId);
        holder.typeText.setBackgroundColor(typeColor);
    }

    @Override
    public int getItemCount() {
        return pokemonType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, typeText;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            typeText = itemView.findViewById(R.id.pokemonTypeView);
        }
        public void bind(PokemonTypes pokemonType){
//            typeText.setTextColor();
            String name = pokemonType.getType().getName();
            typeText.setText(name);
        }
    }
}
