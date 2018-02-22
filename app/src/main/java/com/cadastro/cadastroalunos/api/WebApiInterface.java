package com.cadastro.cadastroalunos.api;

import com.cadastro.cadastroalunos.Aluno;
import com.cadastro.cadastroalunos.pojo.RespostaCriar;
import com.cadastro.cadastroalunos.teste.Cidades;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by bruno on 21/02/2018.
 */

public interface WebApiInterface {

    @GET("cities")
    Call<Cidades> getCidades();

    @GET("alunos/{id}")
    Call<Aluno> getAluno(@Query("id") int matricula);

    @FormUrlEncoded
    @POST
    Call<RespostaCriar> postCriar(@Field("nome") String nome);

}
