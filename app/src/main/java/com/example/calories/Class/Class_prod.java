package com.example.calories.Class;

import java.util.ArrayList;

public class Class_prod {
    private String name;
    private String gramm;
    private ArrayList<Prod> class_prodList = new ArrayList<Prod>();

    public Class_prod(String name, String gramm, ArrayList<Prod> class_prodList) {
        this.name = name;
        this.gramm = gramm;
        this.class_prodList = class_prodList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Prod> getClass_prodList() {
        return class_prodList;
    }

    public void setClass_prodList(ArrayList<Prod> class_prodList) {
        this.class_prodList = class_prodList;
    }

    public String getGramm() {
        return gramm;
    }

    public void setGramm(String gramm) {
        this.gramm = gramm;
    }
}
