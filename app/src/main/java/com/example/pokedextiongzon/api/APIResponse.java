package com.example.pokedextiongzon.api;

import com.example.pokedextiongzon.model.PokemonAbilities;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.model.PokemonSprites;
import com.example.pokedextiongzon.model.PokemonStat;
import com.example.pokedextiongzon.model.PokemonStats;
import com.example.pokedextiongzon.model.PokemonTypes;

import java.util.ArrayList;

public class APIResponse {
    private ArrayList<PokemonList> results;
    public ArrayList<PokemonTypes> types;
    public ArrayList<PokemonStats> stats;

    public void setResults(ArrayList<PokemonList> results) {
        this.results = results;
    }

    public ArrayList<PokemonAbilities> abilities;
    public int id;
    public PokemonSprites sprites;

    public PokemonSprites getSprites() {
        return sprites;
    }

    public int getId() {
        return id;
    }

    public ArrayList<PokemonAbilities> getAbilities() {
        return abilities;
    }

    public ArrayList<PokemonTypes> getTypes() {
        return types;
    }


    public ArrayList<PokemonList> getPokemonResults() {
        return results;
    }

    public ArrayList<PokemonStats> getStats() {
        return stats;
    }

    public void setTypes(ArrayList<PokemonTypes> types) {
        this.types = types;
    }

    public void setStats(ArrayList<PokemonStats> stats) {
        this.stats = stats;
    }

    public void setAbilities(ArrayList<PokemonAbilities> abilities) {
        this.abilities = abilities;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSprites(PokemonSprites sprites) {
        this.sprites = sprites;
    }

    public void setCount(int i) {
    }


}



