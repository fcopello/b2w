package com.b2w.americanas.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fabiocopello on 3/5/16.
 */
public class Product {

    private String id;
    @SerializedName("hasDefaultPrice")
    private boolean hasDefaultPrice;
    private String defaultPrice;
    private String salesPrice;
    private String invoicePrice;
    private String url;
    private String image;
    private String mqImage;
    private String supplier;
    private boolean stock;
    private String name;
    @SerializedName("croppedName")
    private String croppedName;
    private BrandInstallment brandInstallment;
    List<Seller> sellers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasDefaultPrice() {
        return hasDefaultPrice;
    }

    public void setHasDefaultPrice(boolean hasDefaultPrice) {
        this.hasDefaultPrice = hasDefaultPrice;
    }

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(String invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMqImage() {
        return mqImage;
    }

    public void setMqImage(String mqImage) {
        this.mqImage = mqImage;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCroppedName() {
        return croppedName;
    }

    public void setCroppedName(String croppedName) {
        this.croppedName = croppedName;
    }

    public BrandInstallment getBrandInstallment() {
        return brandInstallment;
    }

    public void setBrandInstallment(BrandInstallment brandInstallment) {
        this.brandInstallment = brandInstallment;
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }
}
