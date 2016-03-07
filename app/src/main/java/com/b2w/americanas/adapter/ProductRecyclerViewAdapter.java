package com.b2w.americanas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.b2w.americanas.R;
import com.b2w.americanas.model.Product;
import com.b2w.americanas.utils.Consts;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by fabiocopello on 3/5/16.
 */
public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder> {

    private List<Product> mProducts;
    private Context mContext;

    public ProductRecyclerViewAdapter(List<Product> products, Context context) {
        this.mContext = context;
        this.mProducts = products;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(
                mContext).inflate(R.layout.card_product, viewGroup, false);

        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int i) {
        Product product = mProducts.get(i);

        holder.title.setText(product.getCroppedName());

        if (product.isHasDefaultPrice()) {
            holder.defaultPrice.setText(buildDefaultPrice(product));
            holder.defaultPrice.setVisibility(View.VISIBLE);
        } else {
            holder.defaultPrice.setVisibility(View.INVISIBLE);
        }

        holder.price.setText(product.getSalesPrice());
        holder.installments.setText(buildInstallments(product));

        Picasso.with(mContext).load(product.getImage()).into(holder.image);
    }

    /**
     * Build default price String.
     * @param product
     * @return
     */
    private String buildDefaultPrice(Product product) {
        StringBuilder defaultPrice = new StringBuilder();
        defaultPrice.append(Consts.DEFAULT_PRICE_PREFIX);
        defaultPrice.append(product.getDefaultPrice());
        defaultPrice.append(Consts.DEFAULT_PRICE_SUFIX);

        return defaultPrice.toString();
    }

    /**
     * Build installments String.
     * @param product
     * @return
     */
    private String buildInstallments(Product product) {

        StringBuilder installments = new StringBuilder();
        installments.append(product.getBrandInstallment().getTotalInstallments());
        installments.append(Consts.INSTALLMENTS_SEPARATOR);
        installments.append(product.getBrandInstallment().getInstallmentValue());

        return installments.toString();
    }

    @Override
    public int getItemCount() {
        return this.mProducts.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        View container;
        TextView title;
        ImageView image;
        TextView price;
        TextView defaultPrice;
        TextView installments;

        public ProductViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.item);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
            price = (TextView) itemView.findViewById(R.id.price);
            defaultPrice = (TextView) itemView.findViewById(R.id.default_price);
            installments = (TextView) itemView.findViewById(R.id.installments);
        }
    }

    public void addProducts(List<Product> products, boolean clear) {
        if (clear) {
            this.mProducts.clear();
        }
        this.mProducts.addAll(products);
        notifyDataSetChanged();
    }
}
