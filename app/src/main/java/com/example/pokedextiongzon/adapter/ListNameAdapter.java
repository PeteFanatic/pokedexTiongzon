package com.example.pokedextiongzon.adapter;

//import static com.example.pokedextiongzon.MainActivity.data_url_end;
//import static com.example.pokedextiongzon.MainActivity.data_url_start;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.R;

import java.util.ArrayList;

public class ListNameAdapter extends RecyclerView.Adapter<ListNameAdapter.ViewHolder> {
    Context context;
    ArrayList<PokemonList> pokemonListName;
    private RecyclerViewClickListener listener;
    final static String img_url_start = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    final static String img_url_end = ".png";
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
        int num2;
        String url2;
        private ImageView image;
        private TextView nameText;

        @Override
        public void onClick(View view){
            listener.onClick(view, getBindingAdapterPosition());

        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.pokemonUrl);

            name = itemView.findViewById(R.id.pokemonName);
            itemView.setOnClickListener(this);
            image = itemView.findViewById(R.id.pokemonImage);
        }
        public void bind(PokemonList pokemonList){
            url2 = pokemonList.getUrl();
            num2 = getId(url2);
            String pokeImage = img_url_start + num2 + img_url_end;
            String num = String.format("%03d",num2);
            url.setText(num+"");
            name.setText(pokemonList.getName());

            Glide.with(context).load(pokeImage).into(image);
        }
        public int getId(String url2){
            String[] parts= url2.split("/");
            String num = parts[parts.length - 1];
            int num2 = Integer.parseInt(num);
            return num2;
        }
    }
}
