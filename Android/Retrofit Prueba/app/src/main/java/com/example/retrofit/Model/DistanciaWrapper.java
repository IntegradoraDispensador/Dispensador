package com.example.retrofit.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DistanciaWrapper {


    @SerializedName("groups")
    @Expose
    private List<Example> distancia = null;

    public List<Example> getDistancia() {
        return distancia;
    }

    public void setDistancia(List<Example> distancia) {
        this.distancia = distancia;
    }


}
