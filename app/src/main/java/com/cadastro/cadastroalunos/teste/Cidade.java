package com.cadastro.cadastroalunos.teste;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bruno on 21/02/2018.
 */

public class Cidade {

    @SerializedName("name")
    private String name;

    public Cidade() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
