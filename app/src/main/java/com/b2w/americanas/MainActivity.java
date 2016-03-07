package com.b2w.americanas;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.b2w.americanas.adapter.ProductRecyclerViewAdapter;
import com.b2w.americanas.api.AmericanasAPI;
import com.b2w.americanas.api.response.ProductsResponse;
import com.b2w.americanas.model.Product;
import com.b2w.americanas.utils.Consts;
import com.b2w.americanas.utils.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductRecyclerViewAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        configureRecyclerView();
        requestFirstPage();
    }

    private void requestFirstPage() {
        requestPage(Consts.FIRST_PAGE);
    }

    /**
     * Using static path, so page is useless. The content will be always loaded and repeated.
     * @param page
     */
    private void requestPage(int page) {

        final boolean clear = page == Consts.FIRST_PAGE;

        String itens = Consts.DEFAULT_ITENS;
        AmericanasAPI.getAmericanasService().getProducts(itens, new Callback<ProductsResponse>() {
            @Override
            public void success(ProductsResponse productsResponse, Response response) {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.addProducts(productsResponse.getProducts(), clear);
            }

            @Override
            public void failure(RetrofitError error) {
                mSwipeRefreshLayout.setRefreshing(false);

                Log.d(TAG, error.getLocalizedMessage());

                Toast.makeText(MainActivity.this,
                        R.string.load_products_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Configure recyclerView.
     */
    private void configureRecyclerView() {

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        mLayoutManager = staggeredGridLayoutManager;

        mAdapter = new ProductRecyclerViewAdapter(
                new ArrayList<Product>(), this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        bindEndlessScrollListener(mLayoutManager);
    }

    /**
     * Bind endless scroll listener.
     * @param layoutManager
     */
    private void bindEndlessScrollListener(final RecyclerView.LayoutManager layoutManager) {

        mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                requestPage(currentPage);
            }
        };

        mRecyclerView.addOnScrollListener(mEndlessRecyclerOnScrollListener);
    }

    @Override
    public void onRefresh() {
        mEndlessRecyclerOnScrollListener.reset();
        requestFirstPage();
    }
}
