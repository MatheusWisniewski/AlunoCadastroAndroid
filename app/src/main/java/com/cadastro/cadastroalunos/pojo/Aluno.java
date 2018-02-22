package com.cadastro.cadastroalunos.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by matheus on 21/02/2018.
 */

public class Aluno implements Serializable{

    @SerializedName("id")
    private String id;
    @SerializedName("cpf")
    private String cpf;
    @SerializedName("nome")
    private String nome;
    @SerializedName("idade")
    private int idade;
    @SerializedName("endereco")
    private Endereco endereco;

    public Aluno() {
    }

    public Aluno(String id, String cpf, String nome, int idade, Endereco endereco) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
