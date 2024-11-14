package io.github.gabrielhenriquehe.streetcontroller.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.activities.ActivityCadastroVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.adapters.AdapterVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelVeiculo;

public class FragmentListaVeiculos extends Fragment {

    public ViewModelVeiculo viewModelVeiculo;
    private AdapterVeiculo adapterVeiculo;

    public FragmentListaVeiculos() {

    }

    public static FragmentListaVeiculos newInstance(String param1, String param2) {
        return new FragmentListaVeiculos();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_veiculos, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rcv_lista_veiculos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterVeiculo = new AdapterVeiculo(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapterVeiculo);

        viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);

        viewModelVeiculo.getVeiculos().observe(getViewLifecycleOwner(), veiculos -> {
            if (veiculos != null && !veiculos.isEmpty()) {
                adapterVeiculo.setVeiculos(veiculos);
            } else {
                adapterVeiculo.setVeiculos(new ArrayList<>());
            }
        });

        FloatingActionButton btnCadastrar = view.findViewById(R.id.btn_cadastrar);
        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityCadastroVeiculo.class);
            startActivity(intent);
        });

        return view;
    }
}