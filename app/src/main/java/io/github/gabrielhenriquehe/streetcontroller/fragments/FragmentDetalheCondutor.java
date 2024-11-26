package io.github.gabrielhenriquehe.streetcontroller.fragments;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelCondutor;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelVeiculo;

public class FragmentDetalheCondutor extends Fragment {

    private TextView txtNome, txtCpf, txtDataNascimento, txtVencimentoCNH;
    private Button btnExcluirCondutor;

    private Condutor condutor;

    public FragmentDetalheCondutor() {

    }

    public static FragmentDetalheCondutor newInstance(Condutor condutor) {
        FragmentDetalheCondutor fragment = new FragmentDetalheCondutor();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_condutor, container, false);

        txtNome = view.findViewById(R.id.txt_nome);
        txtCpf = view.findViewById(R.id.txt_cpf);
        txtDataNascimento = view.findViewById(R.id.txt_data_nascimento);
        txtVencimentoCNH = view.findViewById(R.id.txt_vencimento_cnh);
        btnExcluirCondutor = view.findViewById(R.id.btn_excluir_condutor);

        if (condutor != null) {
            String nome = condutor.getPrimeiroNome() + " " + condutor.getUltimoNome();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            txtNome.setText(nome);

            String cpfFormatado = this.formatarCpf(condutor.getCpf());
            txtCpf.setText(cpfFormatado);

            txtDataNascimento.setText(sdf.format(condutor.getDataNascimento()));
            txtVencimentoCNH.setText(sdf.format(condutor.getVencimentoHabilitacao()));

            btnExcluirCondutor.setOnClickListener(v -> excluirCondutor(condutor));
        }

        return view;
    }

    private void excluirCondutor(Condutor condutor) {
        ViewModelVeiculo viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);
        ViewModelCondutor viewModelCondutor = new ViewModelProvider(this).get(ViewModelCondutor.class);

        viewModelVeiculo.getVeiculosByCondutorId(condutor.getId()).observe(getViewLifecycleOwner(), veiculos -> {
            if (veiculos != null && !veiculos.isEmpty()) {
                Toast.makeText(this.getContext(), "Existem veículos vinculados a esse condutor.", Toast.LENGTH_SHORT).show();
            } else {
                new AlertDialog.Builder(requireContext()).setTitle("Confirmar operação")
                        .setMessage("Deseja realmente excluir esse condutor?")
                        .setPositiveButton("Sim", ((dialog, which) -> {
                            viewModelCondutor.delete(condutor);
                            Toast.makeText(this.getContext(), "Condutor excluído com sucesso.", Toast.LENGTH_SHORT).show();
                            requireActivity().finish();
                        }))
                        .setNegativeButton("Não", (dialog, which) -> {
                            dialog.dismiss();
                        }).show();
            }
        });
    }

    private String formatarCpf(String cpf) {
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }

        return cpf;
    }
}