package io.github.gabrielhenriquehe.streetcontroller.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.activities.ActivityDetalheCondutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.CondutorVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.viewholder.ViewHolderCondutor;

public class AdapterCondutor extends RecyclerView.Adapter<ViewHolderCondutor> {

    public Context context;
    public List<CondutorVeiculo> condutoresComVeiculo;

    public AdapterCondutor(Context context, List<CondutorVeiculo> condutores) {
        this.context = context;
        this.condutoresComVeiculo = (condutores != null) ? condutores : new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderCondutor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            throw new IllegalStateException("Context cannot be null");
        }
        return new ViewHolderCondutor(LayoutInflater.from(context).inflate(R.layout.item_condutor_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCondutor holder, int position) {
        CondutorVeiculo condutorVeiculo = condutoresComVeiculo.get(position);

        String nomeCompleto = condutorVeiculo.condutor.getPrimeiroNome().concat(" ").concat(condutorVeiculo.condutor.getUltimoNome());
        String cpfFormatado = this.formatarCpf(condutorVeiculo.condutor.getCpf());

        holder.txtName.setText(nomeCompleto);
        holder.txtCpf.setText(cpfFormatado);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, ActivityDetalheCondutor.class);
            intent.putExtra("condutor_data", condutorVeiculo.condutor);
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return condutoresComVeiculo.size();
    }

    public void setCondutores(List<CondutorVeiculo> condutores) {
        this.condutoresComVeiculo = condutores != null ? condutores : new ArrayList<>();
        notifyDataSetChanged();
    }

    private String formatarCpf(String cpf) {
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }

        return cpf;
    }
}
