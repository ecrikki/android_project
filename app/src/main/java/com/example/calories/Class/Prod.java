package com.example.calories.Class;

public class Prod {

    public String id_product, белки, жиры, углеводы, калорийность;

    public Prod() {
    }

    public Prod(String id_product, String белки, String жиры, String углеводы, String калорийность) {
        this.id_product = id_product;
        this.белки = белки;
        this.жиры = жиры;
        this.углеводы = углеводы;
        this.калорийность = калорийность;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getБелки() {
        return белки;
    }

    public void setБелки(String белки) {
        this.белки = белки;
    }

    public String getЖиры() {
        return жиры;
    }

    public void setЖиры(String жиры) {
        this.жиры = жиры;
    }

    public String getУглеводы() {
        return углеводы;
    }

    public void setУглеводы(String углеводы) {
        this.углеводы = углеводы;
    }

    public String getКалорийность() {
        return калорийность;
    }

    public void setКалорийность(String калорийность) {
        this.калорийность = калорийность;
    }
}
