package com.example.pokedextiongzon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedextiongzon.R;
import com.example.pokedextiongzon.model.PokemonAbilities;

import java.util.ArrayList;

public class ListAbilityAdapter extends RecyclerView.Adapter<ListAbilityAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonAbilities> pokemonAbilities;
    public ListAbilityAdapter(Context context, ArrayList<PokemonAbilities> arrayList) {
        this.context = context;
        this.pokemonAbilities = arrayList;

    }

    @NonNull
    @Override
    public ListAbilityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.type_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAbilityAdapter.ViewHolder holder, int position) {
        //PokemonTypes types = type.get(position);
        holder.abilityText.setText(pokemonAbilities.get(position).getAbility().getName());
        //holder.bind(pokemonType.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return pokemonAbilities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, abilityText, baseStatText;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            abilityText = itemView.findViewById(R.id.pokemonAbilitiesView);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonAbilities pokemonAbilities){
            //url.setText(PokemonType.getUrl()+"");
            abilityText.setText(pokemonAbilities.getAbility().getName());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}