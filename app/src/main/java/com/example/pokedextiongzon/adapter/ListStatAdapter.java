package com.example.pokedextiongzon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedextiongzon.R;
import com.example.pokedextiongzon.model.PokemonStats;

import java.util.ArrayList;

public class ListStatAdapter extends RecyclerView.Adapter<ListStatAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonStats> pokemonStats;
    public ListStatAdapter(Context context, ArrayList<PokemonStats> arrayList) {
        this.context = context;
        this.pokemonStats = arrayList;

    }



    @NonNull
    @Override
    public ListStatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.type_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStatAdapter.ViewHolder holder, int position) {
        //PokemonTypes types = type.get(position);
        holder.statNameText.setText(pokemonStats.get(position).getStat().getName());
        holder.baseStatText.setText(String.valueOf(pokemonStats.get(position).getBase_stat()));
        holder.typeButton.setVisibility(View.INVISIBLE);
        //holder.bind(pokemonType.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return pokemonStats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView url, statNameText, baseStatText;
        private Button typeButton;
        //private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            statNameText = itemView.findViewById(R.id.pokemonStat_NameView);
            baseStatText = itemView.findViewById(R.id.pokemonBase_StatView);
            typeButton = itemView.findViewById(R.id.pokemonTypeView);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonStats pokemonStats){
            //url.setText(PokemonType.getUrl()+"");
            statNameText.setText(pokemonStats.getStat().getName());
            baseStatText.setText("" + pokemonStats.getBase_stat());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
