package com.example.calories.Class;

public class Prod {

    public String id_product, калорийность;

    public Prod() {
    }

    public Prod(String id_product, String калорийность) {
        this.id_product = id_product;
        this.калорийность = калорийность;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getКалорийность() {
        return калорийность;
    }

    public void setКалорийность(String калорийность) {
        this.калорийность = калорийность;
    }
}
