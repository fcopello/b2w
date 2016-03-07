package com.b2w.americanas.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author Unknown
 */
public abstract class EndlessRecyclerOnScrollListener extends
        RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerOnScrollListener.class
            .getSimpleName();

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private RecyclerView.LayoutManager mLinearLayoutManager;

    public EndlessRecyclerOnScrollListener(
            RecyclerView.LayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();

        if (mLinearLayoutManager instanceof LinearLayoutManager) {
            firstVisibleItem = ((LinearLayoutManager)mLinearLayoutManager).findFirstVisibleItemPosition();
        } else if (mLinearLayoutManager instanceof StaggeredGridLayoutManager) {
            int[] firstVisibleItemPositions = ((StaggeredGridLayoutManager) mLinearLayoutManager).findFirstVisibleItemPositions(null);
            if (firstVisibleItemPositions != null || firstVisibleItemPositions.length > 0) {
                firstVisibleItem = firstVisibleItemPositions[0];
            } else {
                firstVisibleItem = 0;
            }
        } else {
            firstVisibleItem = 0;
        }


        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading
                && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
            current_page++;

            onLoadMore(current_page);

            loading = true;
        }
    }

    public void reset() {
        current_page = 1;
        previousTotal = 0;
    }

    public abstract void onLoadMore(int current_page);
}