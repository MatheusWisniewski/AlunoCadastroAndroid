package com.cadastro.cadastroalunos.pojo;

import com.cadastro.cadastroalunos.pojo.Aluno;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by matheus on 21/02/2018.
 */

public class Alunos {

    @SerializedName("alunos")
    List<Aluno> alunos;

    public Alunos() {
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
