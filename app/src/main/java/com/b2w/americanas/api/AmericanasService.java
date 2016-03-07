package com.b2w.americanas.api;

import com.b2w.americanas.api.response.ProductsResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by fabiocopello on 3/5/16.
 */
public interface AmericanasService {

    @GET("/productinfo")
    public void getProducts(@Query("itens") String itens, Callback<ProductsResponse> products);

}
