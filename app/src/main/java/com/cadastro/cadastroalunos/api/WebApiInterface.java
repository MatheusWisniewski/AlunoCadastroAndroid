package com.cadastro.cadastroalunos.api;

import com.cadastro.cadastroalunos.pojo.Aluno;
import com.cadastro.cadastroalunos.pojo.Alunos;
import com.cadastro.cadastroalunos.pojo.RespostaAtualizar;
import com.cadastro.cadastroalunos.pojo.RespostaCriar;
import com.cadastro.cadastroalunos.pojo.RespostaDeletar;

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
    Call<Alunos> getAlunos();

    @GET("alunos/{id}")
    Call<Aluno> getAluno(
            @Query("id") String matricula
    );

    @FormUrlEncoded
    @POST("alunos")
    Call<RespostaCriar> postCriar(
            @Field("nome") String nome,
            @Field("cpf") String cpf,
            @Field("idade") int idade,
            @Field("estado") String estado,
            @Field("cidade") String cidade,
            @Field("bairro") String bairro,
            @Field("logradouro") String logradouro,
            @Field("numero") int numero,
            @Field("complemento") String complemento,
            @Field("cep") String cep
    );

    @FormUrlEncoded
    @PUT("alunos")
    Call<RespostaAtualizar> putAtualizar(
            @Field("id") String id,
            @Field("nome") String nome,
            @Field("cpf") String cpf,
            @Field("idade") int idade,
            @Field("estado") String estado,
            @Field("cidade") String cidade,
            @Field("bairro") String bairro,
            @Field("logradouro") String logradouro,
            @Field("numero") int numero,
            @Field("complemento") String complemento,
            @Field("cep") String cep
    );

    @DELETE("alunos/{id}")
    Call<RespostaDeletar> deleteAluno(
            @Query("id") String matricula
    );
}
