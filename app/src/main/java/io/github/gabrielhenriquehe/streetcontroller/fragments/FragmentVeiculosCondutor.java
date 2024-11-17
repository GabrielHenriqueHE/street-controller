package io.github.gabrielhenriquehe.streetcontroller.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.adapters.AdapterVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.db.AppDatabase;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.entities.VeiculoCondutor;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelVeiculo;

public class FragmentVeiculosCondutor extends Fragment {

    private Condutor condutor;
    private RecyclerView recyclerView;
    private AdapterVeiculo adapterVeiculo;
    private ViewModelVeiculo viewModelVeiculo;

    public FragmentVeiculosCondutor() {
    }

    public static FragmentVeiculosCondutor newInstance(Condutor condutor) {
        FragmentVeiculosCondutor fragment = new FragmentVeiculosCondutor();
        Bundle args = new Bundle();
        args.putSerializable("condutor_data", condutor);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            condutor = (Condutor) getArguments().getSerializable("condutor_data");
        }

        viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_veiculos_condutor, container, false);

        recyclerView = view.findViewById(R.id.rcv_veiculos_condutor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapterVeiculo = new AdapterVeiculo(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapterVeiculo);

        if (condutor != null) {
            loadVeiculos(condutor.getId());
        }

        return view;
    }

    private void loadVeiculos(long id) {
        viewModelVeiculo.getVeiculosByCondutorId(id).observe(getViewLifecycleOwner(), veiculos -> {
            adapterVeiculo = new AdapterVeiculo(getContext(), veiculos);
            recyclerView.setAdapter(adapterVeiculo);
        });
    }
}