package io.github.gabrielhenriquehe.streetcontroller.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.CondutorVeiculo;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelCondutor;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelVeiculo;

public class ActivityCadastroVeiculo extends AppCompatActivity {

    private RadioButton rdbCarro, rdbMoto;
    private EditText edtPlaca, edtMarca, edtModelo, edtAno, edtCor;
    private Button btnFinalizarCadastro;
    private Spinner spinnerCondutor;

    private ViewModelCondutor viewModelCondutor;
    private List<Condutor> condutores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_veiculo);

        rdbCarro = findViewById(R.id.rdb_carro);
        rdbMoto = findViewById(R.id.rdb_moto);

        edtPlaca = findViewById(R.id.txt_placa);
        edtMarca = findViewById(R.id.txt_marca);
        edtModelo = findViewById(R.id.txt_modelo);
        edtAno = findViewById(R.id.txt_ano);
        edtCor = findViewById(R.id.txt_cor);

        spinnerCondutor = findViewById(R.id.spinner_condutor);

        btnFinalizarCadastro = findViewById(R.id.btn_finalizar_cadastro);

        btnFinalizarCadastro.setOnClickListener(this::processRegister);

        viewModelCondutor = new ViewModelProvider(this).get(ViewModelCondutor.class);

        carregarCondutoresSpinner();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void carregarCondutoresSpinner() {
        viewModelCondutor.getCondutores().observe(this, condutoresList -> {
            condutores = condutoresList;
            ArrayAdapter<Condutor> adapter = new ArrayAdapter<Condutor>(this, android.R.layout.simple_spinner_item, condutores);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCondutor.setAdapter(adapter);
        });
    }

    private void processRegister(View v) {
        String placa, marca, modelo, cor, tipo, ano;

        placa = edtPlaca.getText().toString();
        marca = edtMarca.getText().toString();
        modelo = edtModelo.getText().toString();
        ano = edtAno.getText().toString();
        cor = edtCor.getText().toString();
        tipo = rdbCarro.isChecked() ? "CARRO" : "MOTOCICLETA";

        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty() || ano.isEmpty() || cor.isEmpty()) {
            Toast.makeText(v.getContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
        } else {
            Condutor condutorSelecionado = (Condutor) spinnerCondutor.getSelectedItem();

            Veiculo veiculo = new Veiculo();

            veiculo.setPlaca(placa);
            veiculo.setMarca(marca);
            veiculo.setModelo(modelo);
            veiculo.setAno(Integer.parseInt(ano));
            veiculo.setCor(cor);
            veiculo.setTipo(tipo);
            veiculo.setCondutorId(condutorSelecionado != null ? condutorSelecionado.getId() : null);

            ViewModelVeiculo viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);
            viewModelVeiculo.save(veiculo);
            Toast.makeText(v.getContext(), "Veículo registrado com sucesso.", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}