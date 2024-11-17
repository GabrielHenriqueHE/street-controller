package io.github.gabrielhenriquehe.streetcontroller.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;

public class ActivityDetalheVeiculo extends AppCompatActivity {

    TextView txtDescricaoVeiculo, txtTipoVeiculo, txtMarca, txtModelo, txtAno, txtCor, txtPlaca, txtCondutor, txtDocumentoCondutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhe_veiculo);

        txtDescricaoVeiculo = findViewById(R.id.txt_descricao_veiculo);
        txtTipoVeiculo = findViewById(R.id.txt_tipo_veiculo);
        txtMarca =findViewById(R.id.txt_marca);
        txtModelo = findViewById(R.id.txt_modelo);
        txtAno = findViewById(R.id.txt_ano);
        txtCor = findViewById(R.id.txt_cor);
        txtPlaca = findViewById(R.id.txt_placa);
        txtCondutor = findViewById(R.id.txt_condutor);
        txtDocumentoCondutor = findViewById(R.id.txt_documento_condutor);

        Veiculo veiculo = (Veiculo) getIntent().getSerializableExtra("veiculo_data");

        if (veiculo == null) {
            return;
        }

        Condutor condutor = (Condutor) getIntent().getSerializableExtra("condutor_data");

        String descricao = veiculo.getMarca() + " " + veiculo.getModelo();

        txtDescricaoVeiculo.setText(descricao);
        txtTipoVeiculo.setText(veiculo.getTipo());
        txtMarca.setText(veiculo.getMarca());
        txtModelo.setText(veiculo.getModelo());
        txtAno.setText(String.valueOf(veiculo.getAno()));
        txtCor.setText(veiculo.getCor());
        txtPlaca.setText(veiculo.getPlaca());

        if (condutor == null) {
            txtCondutor.setText("--");
            txtDocumentoCondutor.setText("--");
        } else {
            String condutorNome = condutor.getPrimeiroNome() + " " + condutor.getUltimoNome();
            txtCondutor.setText(condutorNome);
            txtDocumentoCondutor.setText(condutor.getCpf());
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}