package io.github.gabrielhenriquehe.streetcontroller.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.gabrielhenriquehe.streetcontroller.R;


public class ViewHolderVeiculo extends RecyclerView.ViewHolder {

    public TextView veiculoInfoView;

    public ViewHolderVeiculo(@NonNull View itemView) {
        super(itemView);
        veiculoInfoView = itemView.findViewById(R.id.veiculo_info);
    }
}
