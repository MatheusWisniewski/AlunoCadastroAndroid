package com.cadastro.cadastroalunos.teste;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bruno on 21/02/2018.
 */

public class Cidades {

    @SerializedName("cities")
    List<Cidade> cidades;

    public Cidades() {
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
