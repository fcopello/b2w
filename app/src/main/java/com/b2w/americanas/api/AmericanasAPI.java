package com.b2w.americanas.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by fabiocopello on 3/5/16.
 */
public class AmericanasAPI {

    private static final String API_ENDPOINT = "http://produto.americanas.com.br";


    private static RestAdapter restAdapter;

    public static AmericanasService getAmericanasService() {
        return getRestAdapter().create(AmericanasService.class);
    }


    private static RestAdapter getRestAdapter() {

        if (restAdapter == null) {

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_ENDPOINT)
                    .setLogLevel(RestAdapter.LogLevel.BASIC)
                    .setConverter(new GsonConverter(gson))
                    .build();
        }

        return restAdapter;

    }
}
