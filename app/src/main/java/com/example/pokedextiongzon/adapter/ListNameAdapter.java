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
    private RecyclerViewClickListener listener;
    public ListNameAdapter(Context context, ArrayList<PokemonList> arrayList, RecyclerViewClickListener listener) {
        this.context = context;
        this.pokemonListName = arrayList;
        this.listener = listener;
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
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView url, name;

        //private ImageView image;
        private TextView nameText;

        @Override
        public void onClick(View view){
            listener.onClick(view, getBindingAdapterPosition());

        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //url = itemView.findViewById(R.id.pokemonUrl);

            name = itemView.findViewById(R.id.pokemonName);
            itemView.setOnClickListener(this);
            //image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonList pokemonList){
            //url.setText(pokemonList.getUrl()+"");
            name.setText(pokemonList.getName());
            //Glide.with(context).load(pokemonList.getImage()).into(image);
        }
    }
}
