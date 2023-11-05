package com.example.pokedextiongzon.model;

public class PokemonStats {
    int base_stat;

    public void setStat(PokemonStat stat) {
        this.stat = stat;
    }

    PokemonStat stat;

    public int getBase_stat() {
        return base_stat;
    }

    public PokemonStat getStat() {
        return stat;
    }


}
