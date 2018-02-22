package com.cadastro.cadastroalunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.cadastro.cadastroalunos.api.Network;
import com.cadastro.cadastroalunos.api.WebApiInterface;
import com.cadastro.cadastroalunos.pojo.RespostaCriar;
import com.cadastro.cadastroalunos.teste.Cidades;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoActivity extends AppCompatActivity {
    Aluno aluno;

    // api
    WebApiInterface webApiInterface;
    Network network = new Network();

    AppCompatButton botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        webApiInterface = network.getRetrofit().create(WebApiInterface.class);
        botaoSalvar = findViewById(R.id.btn_salvar);

        if (getIntent().getBundleExtra("extras") != null) {
            Bundle bundle = getIntent().getExtras();
            Log.d("testando", "onCreate: " + getIntent().hasExtra("extra"));
            if (bundle.getSerializable("aluno") != null)
                aluno = (Aluno) bundle.getSerializable("aluno");

            handlerIsNew();
        }


    }


    private void handlerIsNew() {
        if (aluno == null) {
            botaoSalvar.setText("SALVAR");
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AlunoActivity.this, "SALVAR", Toast.LENGTH_SHORT).show();
                    salvarAluno();
                }
            });
        } else {
            botaoSalvar.setText("EDITAR");
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AlunoActivity.this, "EDITAR", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void salvarAluno() {
        webApiInterface.postCriar(aluno.getNome())
                .enqueue(new Callback<RespostaCriar>() {
            @Override
            public void onResponse(Call<RespostaCriar> call, Response<RespostaCriar> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AlunoActivity.this, "Salvou", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RespostaCriar> call, Throwable t) {
                Toast.makeText(AlunoActivity.this, "Errp " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
