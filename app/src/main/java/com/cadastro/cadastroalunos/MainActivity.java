package com.cadastro.cadastroalunos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cadastro.cadastroalunos.api.Network;
import com.cadastro.cadastroalunos.api.WebApiInterface;
import com.cadastro.cadastroalunos.teste.Cidades;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etSearch;
    RecyclerView rvList;
    FloatingActionButton fabAdd;
    Button btSearch;

    List<Aluno> todosAlunos;
    List<Aluno> alunosFiltrados = new ArrayList<>();
    AlunosAdapter adapter;

    // api
    WebApiInterface webApiInterface;
    Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.et_search);
        rvList = findViewById(R.id.rv_list);
        fabAdd = findViewById(R.id.fab_add);
        btSearch = findViewById(R.id.bt_search);

        // setup recycler view
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);

        // evento de clique
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlunoActivity.class);
                startActivity(intent);
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String filtro = etSearch.getText().toString();
                alunosFiltrados.clear();
                for (Aluno aluno : todosAlunos) {
                    if (aluno.getNome().contains(filtro)) {
                        alunosFiltrados.add(aluno);
                    }
                }

                if (etSearch.length() == 0) {
                    adapter = new AlunosAdapter(todosAlunos);
                    rvList.setAdapter(adapter);
                }

                // filterzinho
                adapter = new AlunosAdapter(alunosFiltrados);
                rvList.setAdapter(adapter);

            }
        });



        // preencher lista
        final List<Aluno> alunos = new ArrayList<>();

        // teste
        final Aluno aluno = new Aluno();
        aluno.setNome("testando");
        alunos.add(aluno);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("bruno");
        alunos.add(aluno2);

        //pegar da api
        webApiInterface = network.getRetrofit().create(WebApiInterface.class);

        webApiInterface.getCidades().enqueue(new Callback<Cidades>() {
            @Override
            public void onResponse(Call<Cidades> call, Response<Cidades> response) {

                // popular o array alunos aqui
                todosAlunos = alunos;
                adapter = new AlunosAdapter(alunos);

                rvList.setAdapter(adapter);

                if (response.isSuccessful())
                    Toast.makeText(MainActivity.this, ">> " + response.body().getCidades().get(0).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Cidades> call, Throwable t) {
                Toast.makeText(MainActivity.this, "vixi falhou" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
