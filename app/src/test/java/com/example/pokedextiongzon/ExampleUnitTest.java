package com.example.pokedextiongzon;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.pokedextiongzon.adapter.ListNameAdapter;
import com.example.pokedextiongzon.api.APIClient;
import com.example.pokedextiongzon.api.APIInterface;
import com.example.pokedextiongzon.api.APIResponse;
import com.example.pokedextiongzon.model.PokemonAbilities;
import com.example.pokedextiongzon.model.PokemonAbility;
import com.example.pokedextiongzon.model.PokemonList;
import com.example.pokedextiongzon.model.PokemonSprites;
import com.example.pokedextiongzon.model.PokemonStat;
import com.example.pokedextiongzon.model.PokemonStats;
import com.example.pokedextiongzon.model.PokemonType;
import com.example.pokedextiongzon.model.PokemonTypes;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExampleUnitTest {
    // Mock the Retrofit API interface
    @Mock
    private APIInterface apiInterface;
    ArrayList<PokemonList> pokemonTestArrayList = new ArrayList<>();
    ArrayList<PokemonStats> pokemonStatsArrayList = new ArrayList<>();
    ArrayList<PokemonStat> pokemonStatArrayList = new ArrayList<>();
    ArrayList<PokemonAbilities> pokemonAbilitiesArrayList = new ArrayList<>();
    ArrayList<PokemonAbility> pokemonAbilityArrayList = new ArrayList<>();
    ArrayList<PokemonTypes> pokemonTypesArrayList = new ArrayList<>();
    ArrayList<PokemonType> pokemonTypeArrayList = new ArrayList<>();
    PokemonSprites pokemonSprites = new PokemonSprites();
    PokemonList pokemon = new PokemonList();
    PokemonStats pokemonStats = new PokemonStats();
    PokemonStat pokemonStat = new PokemonStat();
    PokemonAbilities pokemonAbilities = new PokemonAbilities();
    PokemonAbility pokemonAbility = new PokemonAbility();
    PokemonTypes pokemonTypes = new PokemonTypes();
    PokemonType pokemonType = new PokemonType();
    private final String pokemonName = "bulbasaur";
    private final String[] pokemonStatsName = {"hp","attack","defense","special-attack","special-defense","speed"};
    private final String[] pokemonTypesName = {"grass","poison"};
    private final String[] pokemonAbilitiesName = {"overgrow","chlorophyll"};
    int offset = 0;
    int pokemonListCount = 1292;
    int pokemonId = 1;
    String baseUrl_start = "https://pokeapi.co/api/v2/pokemon/";
    String baseUrl_end = "/";
    String pokemonImage_start = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    String pokemonImage_end = ".png";

    @Mock
    private Call<APIResponse> mockCall;
    APIResponse dummyResponse = new APIResponse();
    private APIClient apiService;
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        apiService = new APIClient();
        apiService.apiInterface = apiInterface;
    }

    @Test
    public void testFetchPokemonData() throws IOException {
        dummyResponse.setCount(pokemonListCount);
        pokemon.setName(pokemonName);
        pokemon.setUrl(baseUrl_start+pokemonId+baseUrl_end);
        pokemonTestArrayList.add(pokemon);
        dummyResponse.setResults(pokemonTestArrayList);

        mokitoTest(dummyResponse);

        APIResponse response = apiService.fetchPokemonData(offset,0);
        Assert.assertNotNull(response);
        assertPokemonListEquals(dummyResponse.getPokemonResults().get(0), response.getPokemonResults().get(0));
    }
    @Test
    public void testFetchPokemonAbility() throws IOException{
//        for(int i = 0; i<pokemonAbilitiesName.length;i++){
            pokemonAbility.setName(pokemonAbilitiesName[0]);
            APIResponse response = apiService.fetchPokemonInfo(pokemonName);
            abilityInitializer(pokemonAbility);
            Assert.assertNotNull(response);
//            assertArrayEquals(dummyResponse.getAbilities(),response.getAbilities());
            assertPokemonAbilityEquals(dummyResponse.getAbilities().get(0), response.getAbilities().get(0));
//        }
    }
    @Test
    public void testFetchPokemonTypes() throws IOException{
        for(int i = 0; i<pokemonTypesName.length;i++){
            pokemonType.setName(pokemonTypesName[i]);
            APIResponse response = apiService.fetchPokemonInfo(pokemonName);
            typesInitializer(pokemonType);
            Assert.assertNotNull(response);
            assertPokemonTypeEquals(dummyResponse.getTypes().get(0), response.getTypes().get(i));
        }
    }
    @Test
    public void testFetchPokemonStats()throws IOException{
        APIResponse response = apiService.fetchPokemonInfo(pokemonName);
        for(int i = 0; i<pokemonStatsName.length;i++){
            pokemonStat.setName(pokemonStatsName[i]);
            statsInitializer(pokemonStat);
            Assert.assertNotNull(response);
            assertPokemonStatEquals(dummyResponse.getStats().get(0), response.getStats().get(i));
        }
    }
    @Test
    public void testPokemonImage() throws IOException {
        pokemonSprites.setFront_default(pokemonImage_start+pokemonId+pokemonImage_end);
        dummyResponse.setSprites(pokemonSprites);
        mokitoTest(dummyResponse);
        APIResponse response = apiService.fetchPokemonInfo(pokemonName);
            Assert.assertNotNull(response);
        assertPokemonSpritesEquals(dummyResponse.getSprites(), response.getSprites());
    }
    @Test
    public void testPokemonId() throws IOException{
        dummyResponse.setCount(1);
        dummyResponse.setId(pokemonId);
        mokitoTest(dummyResponse);
        APIResponse response = apiService.fetchPokemonInfo(pokemonName);
        Assert.assertNotNull(response);
        Assert.assertEquals(dummyResponse.getId(), response.getId());
    }
    public void typesInitializer(PokemonType pokemonType){
        pokemonTypeArrayList.add(pokemonType);
        pokemonTypes.setType(pokemonType);
        pokemonTypesArrayList.add(pokemonTypes);
        dummyResponse.setTypes(pokemonTypesArrayList);
        mokitoTest(dummyResponse);
    }
    public void statsInitializer(PokemonStat pokemonStat){
        pokemonStatArrayList.add(pokemonStat);
        pokemonStats.setStat(pokemonStat);
        pokemonStatsArrayList.add(pokemonStats);
        dummyResponse.setStats(pokemonStatsArrayList);
        mokitoTest(dummyResponse);
    }
    public void abilityInitializer(PokemonAbility pokemonAbility){
        pokemonAbilityArrayList.add(pokemonAbility);
        pokemonAbilities.setAbility(pokemonAbility);
        pokemonAbilitiesArrayList.add(pokemonAbilities);
        dummyResponse.setAbilities(pokemonAbilitiesArrayList);
        mokitoTest(dummyResponse);
    }
    public static void assertPokemonListEquals(PokemonList expected, PokemonList actual){
        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getUrl(),actual.getUrl());
    }
    public static void assertPokemonAbilityEquals(PokemonAbilities expected, PokemonAbilities actual){
        assertEquals(expected.getAbility().getName(),actual.getAbility().getName());
    }
    public static void assertPokemonStatEquals(PokemonStats expected, PokemonStats actual){
        assertEquals(expected.getStat().getName(),actual.getStat().getName());
    }
    public static void assertPokemonTypeEquals(PokemonTypes expected, PokemonTypes actual){
        assertEquals(expected.getType().getName(),actual.getType().getName());
    }
    public static void assertPokemonSpritesEquals(PokemonSprites expected, PokemonSprites actual){
        assertEquals(expected.getFront_default(),actual.getFront_default());
    }
    public void mokitoTest(APIResponse dummyResponse){
        Mockito.when(apiInterface.fetchPokemonList(Mockito.anyInt(),Mockito.anyInt())).thenReturn(mockCall);

        Mockito.doAnswer(invocation ->{
            Callback<APIResponse> callback = invocation.getArgument(0);
            callback.onResponse(mockCall, Response.success(dummyResponse));
            return null;
        }).when(mockCall).enqueue(Mockito.any());
    }
}