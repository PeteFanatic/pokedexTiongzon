package com.example.pokedextiongzon.api;

import static org.junit.Assert.assertEquals;

import static kotlinx.coroutines.BuildersKt.runBlocking;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Retrofit;

@RunWith(AndroidJUnit4ClassRunner.class)
public class IsolationTest extends TestCase {
    @Test
    public void test_pokemon_Api_Detail_Result_Success() {
        APIInterface api = TestApiImplementation.provideApi();
        TestApiImplementation test = runBlocking(() -> api.fetchPokemonInfo("1"));
        assertEquals(test.code() == 200, true);
    }

    @Test
    public void test_pokemon_Api_List_Result_Success() {
        TestApi api = TestApiImplementation.provideApi();
        TestApiImplementation.TestApiCall<APIResponse> test = runBlocking(() -> api.getPokemonList());
        assertEquals(test.isSuccessful(), true);
    }

    @Test
    public void test_pokemon_Api_List_Result_NotEmpty() {
        TestApi api = TestApiImplementation.provideApi();
        TestApiImplementation.TestApiCall<APIResponse> test = runBlocking(() -> api.getPokemonList());
        assertEquals(test.body().getResults().isEmpty(), false);
    }

    @Test
    public void test_pokemon_Api_Detail_Result_Not_Null() {
        TestApi api = TestApiImplementation.provideApi();
        TestApiImplementation.TestApiCall<ApiResponse> test = runBlocking(() -> api.getPokemonDetail("1"));
        assertEquals(test.body() != null, true);
    }
}
