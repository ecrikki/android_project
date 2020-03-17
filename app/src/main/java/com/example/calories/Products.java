package com.example.calories;

public class Products {
    public String белки, жиры, углеводы;
    public Long калорийность;

    public Products() {
    }

    public Products(String белки, String жиры, String углеводы, Long калорийность) {
        this.белки = белки;
        this.жиры = жиры;
        this.углеводы = углеводы;
        this.калорийность = калорийность;
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

    public Long getКалорийность() {
        return калорийность;
    }

    public void setКалорийность(Long калорийность) {
        this.калорийность = калорийность;
    }
}
