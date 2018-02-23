package com.cadastro.cadastroalunos.api;

import com.cadastro.cadastroalunos.pojo.Aluno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by matheus on 21/02/2018.
 */

public interface WebApiInterface {

    @GET("alunos")
    Call<List<Aluno>> getAlunos();

    @GET("alunos/{id}")
    Call<Aluno> getAluno(
            @Query("id") String matricula
    );

    @FormUrlEncoded
    @POST("alunos")
    Call<String> postCriar(
            @Field("aluno") Aluno aluno
    );

    @FormUrlEncoded
    @PUT("alunos/{matricula}")
    Call<String> putAtualizar(
            @Query("matricula") String id,
            @Field("aluno") Aluno aluno
    );

    @DELETE("alunos/{id}")
    Call<Aluno> deleteAluno(
            @Query("id") String matricula
    );
}
