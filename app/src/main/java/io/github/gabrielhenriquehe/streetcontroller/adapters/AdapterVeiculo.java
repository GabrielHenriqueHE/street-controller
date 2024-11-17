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
import io.github.gabrielhenriquehe.streetcontroller.activities.ActivityDetalheVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.entities.VeiculoCondutor;
import io.github.gabrielhenriquehe.streetcontroller.viewholder.ViewHolderVeiculo;

public class AdapterVeiculo extends RecyclerView.Adapter<ViewHolderVeiculo> {

    public Context context;
    public List<VeiculoCondutor> veiculoComCondutor;

    public AdapterVeiculo(Context context, List<VeiculoCondutor> veiculos) {
        this.context = context;
        this.veiculoComCondutor = (veiculos != null) ? veiculos : new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderVeiculo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            throw new IllegalStateException("Context cannot be null");
        }

        return new ViewHolderVeiculo(LayoutInflater.from(context).inflate(R.layout.item_veiculo_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderVeiculo holder, int position) {
        String marca, modelo, cor;
        int ano;

        Veiculo veiculo = veiculoComCondutor.get(position).veiculo;
        Condutor condutor = veiculoComCondutor.get(position).condutor;

        marca = veiculo.getMarca();
        modelo = veiculo.getModelo();
        cor = veiculo.getCor();
        ano = veiculo.getAno();
        String info = marca + " " + modelo + " " + ano + " | " + cor;

        holder.veiculoInfoView.setText(info);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ActivityDetalheVeiculo.class);
            intent.putExtra("veiculo_data", veiculo);
            intent.putExtra("condutor_data", condutor);

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return veiculoComCondutor.size();
    }

    public void setVeiculos(List<VeiculoCondutor> veiculos) {
        this.veiculoComCondutor = veiculos != null ? veiculos : new ArrayList<>();
        notifyDataSetChanged();
    }
}
