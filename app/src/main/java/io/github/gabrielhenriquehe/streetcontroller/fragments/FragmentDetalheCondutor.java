package io.github.gabrielhenriquehe.streetcontroller.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;

public class FragmentDetalheCondutor extends Fragment {

    private TextView txtNome, txtCpf, txtDataNascimento, txtVencimentoCNH;

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

        Log.d("TEST", condutor.getPrimeiroNome());

        txtNome = view.findViewById(R.id.txt_nome);
        txtCpf = view.findViewById(R.id.txt_cpf);
        txtDataNascimento = view.findViewById(R.id.txt_data_nascimento);
        txtVencimentoCNH = view.findViewById(R.id.txt_vencimento_cnh);

        if (condutor != null) {
            String nome = condutor.getPrimeiroNome() + " " + condutor.getUltimoNome();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            txtNome.setText(nome);
            txtCpf.setText(condutor.getCpf());
            txtDataNascimento.setText(sdf.format(condutor.getDataNascimento()));
            txtVencimentoCNH.setText(sdf.format(condutor.getVencimentoHabilitacao()));
        }

        return view;
    }
}