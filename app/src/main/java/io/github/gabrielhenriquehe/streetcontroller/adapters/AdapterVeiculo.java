package io.github.gabrielhenriquehe.streetcontroller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.viewholder.ViewHolderVeiculo;

public class AdapterVeiculo extends RecyclerView.Adapter<ViewHolderVeiculo> {

    public Context context;
    public List<Veiculo> veiculos;

    public AdapterVeiculo(Context context, List<Veiculo> veiculos) {
        this.context = context;
        this.veiculos = (veiculos != null) ? veiculos : new ArrayList<>();
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

        marca = veiculos.get(position).getMarca();
        modelo = veiculos.get(position).getModelo();
        cor = veiculos.get(position).getCor();
        ano = veiculos.get(position).getAno();

        holder.veiculoInfoView.setText(
                marca.concat(" ")
                        .concat(modelo)
                        .concat(" ")
                        .concat(String.valueOf(ano))
                        .concat(" | ")
                        .concat(cor));
    }

    @Override
    public int getItemCount() {
        return veiculos.size();
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos != null ? veiculos : new ArrayList<>();
        notifyDataSetChanged();
    }
}
