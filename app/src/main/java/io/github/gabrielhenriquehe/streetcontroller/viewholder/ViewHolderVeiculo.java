package io.github.gabrielhenriquehe.streetcontroller.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.gabrielhenriquehe.streetcontroller.R;


public class ViewHolderVeiculo extends RecyclerView.ViewHolder {

    public TextView txtModelo, txtMarca, txtPlaca, txtAno;
    public ImageView vehicleIcon;

    public ViewHolderVeiculo(@NonNull View itemView) {
        super(itemView);
        txtModelo = itemView.findViewById(R.id.item_txt_modelo);
        txtMarca = itemView.findViewById(R.id.item_txt_marca);
        txtPlaca = itemView.findViewById(R.id.item_txt_placa);
        txtAno = itemView.findViewById(R.id.item_txt_ano);
        vehicleIcon = itemView.findViewById(R.id.icon_vehicle);
    }
}
