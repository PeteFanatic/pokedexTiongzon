package com.example.pokedextiongzon.util.testsuites;

import com.example.pokedextiongzon.api.ApiIsolationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class MainTestSuite {
    @RunWith(Suite.class)
    @Suite.SuiteClasses({
            ApiIsolationTests.class,
            RoomDatabaseTest.class,
            UtilityMethodsTest.class
    })
    public class MainTestSuite {
    }
}
