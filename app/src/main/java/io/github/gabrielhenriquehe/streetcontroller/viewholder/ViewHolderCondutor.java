package io.github.gabrielhenriquehe.streetcontroller.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.gabrielhenriquehe.streetcontroller.R;

public class ViewHolderCondutor extends RecyclerView.ViewHolder {

    public TextView nameView;

    public ViewHolderCondutor(@NonNull View itemView) {
        super(itemView);
        this.nameView = itemView.findViewById(R.id.condutor_nome);
    }
}
