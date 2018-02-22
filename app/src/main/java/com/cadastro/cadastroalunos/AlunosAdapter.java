package com.cadastro.cadastroalunos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cadastro.cadastroalunos.pojo.Aluno;

import java.util.List;

/**
 * Created by matheus on 21/02/2018.
 */

public class AlunosAdapter extends RecyclerView.Adapter<AlunoViewHolder> {

    private List<Aluno> alunos;

    public AlunosAdapter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_aluno, parent, false);
        return new AlunoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        holder.preencherItem(alunos.get(position));
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }
}
