package com.example.calories.Class;

import java.io.Serializable;

public class Prod implements Serializable {

    public String id_product, калорийность, gramm;

    public Prod() {
    }

    public Prod(String id_product, String калорийность, String gramm) {
        this.id_product = id_product;
        this.калорийность = калорийность;
        this.gramm = gramm;
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

    public String getGramm() {
        return gramm;
    }

    public void setGramm(String gramm) {
        this.gramm = gramm;
    }
}
