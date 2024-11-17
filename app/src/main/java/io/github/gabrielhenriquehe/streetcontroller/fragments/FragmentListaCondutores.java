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
import io.github.gabrielhenriquehe.streetcontroller.activities.ActivityCadastroCondutor;
import io.github.gabrielhenriquehe.streetcontroller.adapters.AdapterCondutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.CondutorVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelCondutor;

public class FragmentListaCondutores extends Fragment {

    public ViewModelCondutor viewModelCondutor;
    private AdapterCondutor adapterCondutor;

    public FragmentListaCondutores() {

    }

    public static FragmentListaCondutores newInstance(String param1, String param2) {
        return new FragmentListaCondutores();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_condutores, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rcv_lista_condutores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterCondutor = new AdapterCondutor(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapterCondutor);

        viewModelCondutor = new ViewModelProvider(this).get(ViewModelCondutor.class);

        viewModelCondutor.getCondutoresComVeiculos().observe(getViewLifecycleOwner(), condutores -> {
            if (condutores != null && !condutores.isEmpty()) {
                adapterCondutor.setCondutores(condutores);
            } else {
                adapterCondutor.setCondutores(new ArrayList<CondutorVeiculo>());
            }
        });

        FloatingActionButton btnCadastrarCondutor = view.findViewById(R.id.btn_cadastrar);
        btnCadastrarCondutor.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityCadastroCondutor.class);
            startActivity(intent);
        });

        return view;
    }
}