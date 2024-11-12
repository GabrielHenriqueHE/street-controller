package io.github.gabrielhenriquehe.streetcontroller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.viewholder.ViewHolderCondutor;

public class AdapterCondutor extends RecyclerView.Adapter<ViewHolderCondutor> {

    public Context context;
    public List<Condutor> condutores;

    public AdapterCondutor(Context context, List<Condutor> condutores) {
        this.context = context;
        this.condutores = (condutores != null) ? condutores : new ArrayList<>();
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
        holder.nameView.setText(condutores.get(position).getPrimeiroNome().concat(" ").concat(condutores.get(position).getUltimoNome()));
    }

    @Override
    public int getItemCount() {
        return condutores.size();
    }

    public void setCondutores(List<Condutor> condutores) {
        this.condutores = condutores != null ? condutores : new ArrayList<>();
        notifyDataSetChanged();
    }
}
