package com.b2w.americanas.api.response;

import com.b2w.americanas.model.Product;

import java.util.List;

/**
 * Created by fabiocopello on 3/5/16.
 */
public class ProductsResponse {

    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
