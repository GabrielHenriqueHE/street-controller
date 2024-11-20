package io.github.gabrielhenriquehe.streetcontroller.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.gabrielhenriquehe.streetcontroller.R;

public class ViewHolderCondutor extends RecyclerView.ViewHolder {

    public TextView txtName, txtCpf;

    public ViewHolderCondutor(@NonNull View itemView) {
        super(itemView);
        this.txtName = itemView.findViewById(R.id.item_txt_nome_condutor);
        this.txtCpf = itemView.findViewById(R.id.item_txt_cpf);
    }
}
