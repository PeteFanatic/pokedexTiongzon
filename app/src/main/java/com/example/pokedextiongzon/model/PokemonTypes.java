package com.example.pokedextiongzon.model;

import java.util.ArrayList;

public class PokemonTypes {

    int slot;

    public void setType(PokemonType type) {
        this.type = type;
    }

    PokemonType type;

    public PokemonType getType() {
        return type;
    }

    public int getSlot() { return slot; }


}

