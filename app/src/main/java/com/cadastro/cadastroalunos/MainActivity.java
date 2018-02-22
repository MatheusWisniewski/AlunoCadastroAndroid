package com.cadastro.cadastroalunos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cadastro.cadastroalunos.api.Network;
import com.cadastro.cadastroalunos.api.WebApiInterface;
import com.cadastro.cadastroalunos.pojo.Aluno;
import com.cadastro.cadastroalunos.pojo.Alunos;
import com.cadastro.cadastroalunos.pojo.Endereco;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MAASO";

    EditText etSearch;
    RecyclerView rvList;
    FloatingActionButton fabAdd;
    Button btSearch;

    List<Aluno> todosAlunos = new ArrayList<>();
    List<Aluno> alunosFiltrados = new ArrayList<>();
    AlunosAdapter adapter;

    // api
    WebApiInterface webApiInterface;
    Network network = new Network();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webApiInterface = network.getRetrofit().create(WebApiInterface.class);

        setupViews();

        setupListeners();

        // ---------------------------------------
        // teste
        final Aluno aluno = new Aluno();
        aluno.setNome("Matheus");
        aluno.setId("1");
        aluno.setCpf("08801521928");
        aluno.setIdade(25);
        aluno.setEndereco(new Endereco("rua 1", 2, "casa 22", "bairro legal", "81270010", "Curitiba", "Paraná"));
        todosAlunos.add(aluno);

        Aluno aluno2 = new Aluno();
        aluno2.setNome("Bruno");
        aluno2.setId("2");
        aluno2.setCpf("99999999");
        aluno2.setIdade(25);
        aluno2.setEndereco(new Endereco("rua 2", 3, "casa 32", "bairro chato", "81270010", "Curitiba", "Paraná"));

        todosAlunos.add(aluno2);

        adapter = new AlunosAdapter(todosAlunos);
        rvList.setAdapter(adapter);
        // ---------------------------------------------

        //getAlunos();
    }

    private void setupViews() {
        etSearch = findViewById(R.id.et_search);
        rvList = findViewById(R.id.rv_list);
        fabAdd = findViewById(R.id.fab_add);
        btSearch = findViewById(R.id.bt_search);

        // setup recycler view
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setHasFixedSize(true);
    }

    private void setupListeners() {
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
                Log.d(TAG, "onClick: " + filtro.length());
                alunosFiltrados.clear();

                for (Aluno aluno : todosAlunos) {
                    if (aluno.getId().equals(filtro)) {
                        alunosFiltrados.add(aluno);
                    }
                }

                if (filtro.length() == 0) {
                    adapter = new AlunosAdapter(todosAlunos);
                    rvList.setAdapter(adapter);
                }
                else {
                    adapter = new AlunosAdapter(alunosFiltrados);
                    rvList.setAdapter(adapter);
                }
            }
        });
    }

    private void getAlunos() {
        webApiInterface.getAlunos().enqueue(new Callback<Alunos>() {
            @Override
            public void onResponse(Call<Alunos> call, Response<Alunos> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Get alunos feito com sucesso!", Toast.LENGTH_SHORT).show();

                    todosAlunos = response.body().getAlunos();
                    adapter = new AlunosAdapter(todosAlunos);
                    rvList.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Alunos> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
