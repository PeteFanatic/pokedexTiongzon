package com.example.pokedextiongzon.api;

import com.example.pokedextiongzon.model.PokemonAbilities;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.model.PokemonSprites;
import com.example.pokedextiongzon.model.PokemonStat;
import com.example.pokedextiongzon.model.PokemonType;

import java.util.ArrayList;

public class APIResponse {
    private ArrayList<PokemonList> results;
    private ArrayList<PokemonStat> stats;
    private ArrayList<PokemonType> types;
    private ArrayList<PokemonAbilities> abilities;
    private ArrayList<PokemonSprites> sprites;
    private ArrayList<PokemonList> url;
    public String name;

    public String getName() {
        return name;
    }

    public ArrayList<PokemonList> getUrl() {
        return url;
    }

    public ArrayList<PokemonList> getPokemonResults() {
        return results;
    }
    public ArrayList<PokemonStat> getStats() { return stats; }
    public ArrayList<PokemonType> getTypes() { return types; }
    public ArrayList<PokemonAbilities> getAbilities() { return abilities; }
    public ArrayList<PokemonSprites> getSprites() { return sprites; }
    //public ArrayList<PokemonListId> getId() { return id; }
}
