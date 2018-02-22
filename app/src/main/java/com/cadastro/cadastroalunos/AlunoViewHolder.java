package com.cadastro.cadastroalunos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.nio.channels.InterruptedByTimeoutException;

/**
 * Created by bruno on 21/02/2018.
 */

public class AlunoViewHolder extends RecyclerView.ViewHolder {

    TextView tvNome;
    TextView tvMatricula;
    TextView tvCpf;
    LinearLayout llItem;

    Context context;

    public AlunoViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        tvNome = itemView.findViewById(R.id.tv_nome);
        tvMatricula = itemView.findViewById(R.id.tv_matricula);
        tvCpf = itemView.findViewById(R.id.tv_cpf);
        llItem = itemView.findViewById(R.id.ll_item);
    }

    public void preencherItem(final Aluno aluno) {
        tvNome.setText(aluno.getNome());
        tvMatricula.setText(aluno.getId());
        tvCpf.setText(aluno.getCpf());

        llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AlunoActivity.class);
                Bundle bundle = new Bundle();
                // enviar um aluno para a activity detalhes
                bundle.putSerializable("aluno", aluno);
                intent.putExtra("extras", bundle);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }

        });

    }

}
