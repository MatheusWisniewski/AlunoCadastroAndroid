package com.cadastro.cadastroalunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cadastro.cadastroalunos.api.Network;
import com.cadastro.cadastroalunos.api.WebApiInterface;
import com.cadastro.cadastroalunos.pojo.Aluno;
import com.cadastro.cadastroalunos.pojo.Endereco;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlunoActivity extends AppCompatActivity {

    Aluno aluno;

    WebApiInterface webApiInterface;
    Network network = new Network();

    TextView tvId;
    AppCompatButton botaoSalvar;
    AppCompatButton botaoDeletar;
    AppCompatButton botaoCancelar;
    EditText etNome;
    EditText etCpf;
    EditText etIdade;
    EditText etEstado;
    EditText etCidade;
    EditText etBairro;
    EditText etLogradouro;
    EditText etNumero;
    EditText etComplemento;
    EditText etCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        webApiInterface = network.getRetrofit().create(WebApiInterface.class);

        setupViews();
        setupListeners();

        if (getIntent().getBundleExtra("extras") != null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle.getSerializable("aluno") != null)
                aluno = (Aluno) bundle.getSerializable("aluno");
        }

        handlerIsNew();
    }

    private void setupViews() {
        tvId = findViewById(R.id.tv_id);
        botaoSalvar = findViewById(R.id.btn_salvar);
        botaoDeletar = findViewById(R.id.btn_deletar);
        botaoCancelar = findViewById(R.id.btn_cancelar);
        etNome = findViewById(R.id.input_nome);
        etCpf = findViewById(R.id.input_cpf);
        etIdade = findViewById(R.id.input_idade);
        etEstado = findViewById(R.id.input_estado);
        etCidade = findViewById(R.id.input_cidade);
        etBairro = findViewById(R.id.input_bairro);
        etLogradouro = findViewById(R.id.input_logradouro);
        etNumero = findViewById(R.id.input_numero);
        etComplemento = findViewById(R.id.input_complemento);
        etCep = findViewById(R.id.input_cep);
    }

    private void setupListeners() {
        botaoDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webApiInterface.deleteAluno(aluno.getId())
                        .enqueue(new Callback<Aluno>() {
                            @Override
                            public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(AlunoActivity.this, "Deletado com sucesso!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<Aluno> call, Throwable t) {
                                Toast.makeText(AlunoActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setViewValues() {
        tvId.setText(aluno.getId());
        etNome.setText(aluno.getNome());
        etCpf.setText(aluno.getCpf());
        etIdade.setText(String.valueOf(aluno.getIdade()));
        etEstado.setText(aluno.getEndereco().getEstado());
        etCidade.setText(aluno.getEndereco().getCidade());
        etBairro.setText(aluno.getEndereco().getBairro());
        etLogradouro.setText(aluno.getEndereco().getLogradouro());
        etNumero.setText(String.valueOf(aluno.getEndereco().getNumero()));
        etComplemento.setText(aluno.getEndereco().getComplemento());
        etCep.setText(aluno.getEndereco().getCep());
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
            botaoDeletar.setVisibility(View.GONE);
            tvId.setText("NOVO ALUNO");
        }
        else {
            botaoSalvar.setText("EDITAR");
            botaoSalvar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AlunoActivity.this, "EDITAR", Toast.LENGTH_SHORT).show();
                    atualizarAluno();
                }
            });
            setViewValues();
        }
    }

    private void salvarAluno() {
        webApiInterface.postCriar(
                new Aluno(
                        "Novo",
                        etCpf.getText().toString(),
                        etNome.getText().toString(),
                        Integer.parseInt(etIdade.getText().toString()),
                        new Endereco(
                                etLogradouro.getText().toString(),
                                Integer.parseInt(etNumero.getText().toString()),
                                etComplemento.getText().toString(),
                                etBairro.getText().toString(),
                                etCep.getText().toString(),
                                etCidade.getText().toString(),
                                etEstado.getText().toString()
                        )
                )
        )
        .enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AlunoActivity.this, "Criado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AlunoActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void atualizarAluno() {
        webApiInterface.putAtualizar(
                aluno.getId(),
                new Aluno(
                    aluno.getId(),
                    etCpf.getText().toString(),
                    etNome.getText().toString(),
                    Integer.parseInt(etIdade.getText().toString()),
                    new Endereco(
                            etLogradouro.getText().toString(),
                            Integer.parseInt(etNumero.getText().toString()),
                            etComplemento.getText().toString(),
                            etBairro.getText().toString(),
                            etCep.getText().toString(),
                            etCidade.getText().toString(),
                            etEstado.getText().toString()
                    )
                )

        )
        .enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AlunoActivity.this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AlunoActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
