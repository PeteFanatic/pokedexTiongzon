package com.example.pokedextiongzon;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.api.APIClient;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonList;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokedexApiServiceTest {
    // Mock the Retrofit API interface
    @Mock
    private APIInterface apiInterface;

    // Mock the Retrofit call
    @Mock
    private Call<APIResponse> mockCall;
    int offset = 0;
    int limit = 100;
    ListNameAdapter listNameAdapter;

    // The class under test
    private APIClient apiService;

    @Before
    public void setup() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Create the PokedexApiService instance with the mocked API interface
        apiService = new APIClient();
    }
    @Test
    public void testFetchPokemonData() throws IOException {

        // Create a dummy response
        APIResponse dummyResponse = new APIResponse();
        dummyResponse.getPokemonResults();
        ArrayList<PokemonList> pokemonListArrayList = new ArrayList<>();

        PokemonList pikachu = new PokemonList();
        pikachu.setName("pikachu");
        pikachu.setUrl("https://pokeapi.co/api/v2/pokemon/25/");
        pokemonListArrayList.add(pikachu);

        PokemonList charmander = new PokemonList();
        charmander.setName("charmander");
        charmander.setUrl("https://pokeapi.co/api/v2/pokemon/4/");
        pokemonListArrayList.add(charmander);

        dummyResponse.getPokemonResults();

        // Mock the Retrofit API call
        Mockito.when(apiInterface.fetchPokemonList(limit,offset)).thenReturn(mockCall);

        // Mock the API call's response
        Mockito.doAnswer(invocation -> {
            Callback<APIResponse> callback = invocation.getArgument(0);
            callback.onResponse(mockCall, Response.success(dummyResponse));
            return null;
        }).when(mockCall).enqueue(Mockito.any());

        // Call the method under test
        APIResponse response = apiService.fetchPokemonData();
        // Verify that the response matches the dummy response
        Assert.assertEquals(dummyResponse, response);
    }
}