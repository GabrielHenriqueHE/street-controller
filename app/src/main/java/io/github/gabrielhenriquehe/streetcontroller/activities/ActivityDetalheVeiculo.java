package io.github.gabrielhenriquehe.streetcontroller.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelCondutor;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelVeiculo;

public class ActivityDetalheVeiculo extends AppCompatActivity {

    TextView txtDescricaoVeiculo, txtTipoVeiculo, txtMarca, txtModelo, txtAno, txtCor, txtPlaca, txtCondutor, txtDocumentoCondutor;
    Button btnAdicionarCondutor, btnEditarCondutor, btnRemoverCondutor, btnExcluirVeiculo, btnBack;

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

        btnAdicionarCondutor = findViewById(R.id.btn_adicionar_condutor);
        btnEditarCondutor = findViewById(R.id.btn_editar_condutor);
        btnRemoverCondutor = findViewById(R.id.btn_remover_condutor);
        btnExcluirVeiculo = findViewById(R.id.btn_excluir_veiculo);
        btnBack = findViewById(R.id.btn_back);

        btnBack.setOnClickListener(v -> {
            this.finish();
        });

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

        btnAdicionarCondutor.setOnClickListener(v -> showDialogAddEditCondutor(veiculo));
        btnEditarCondutor.setOnClickListener(v -> showDialogAddEditCondutor(veiculo));
        btnRemoverCondutor.setOnClickListener(v -> removerCondutorVinculado(veiculo));

        btnExcluirVeiculo.setOnClickListener(v -> excluirVeiculo(veiculo));

        if (condutor == null) {
            txtCondutor.setText("--");
            txtDocumentoCondutor.setText("--");

            btnAdicionarCondutor.setVisibility(View.VISIBLE);
            btnExcluirVeiculo.setVisibility(View.VISIBLE);
        } else {
            String condutorNome = condutor.getPrimeiroNome() + " " + condutor.getUltimoNome();
            txtCondutor.setText(condutorNome);
            txtDocumentoCondutor.setText(this.formatarCpf(condutor.getCpf()));

            btnEditarCondutor.setVisibility(View.VISIBLE);
            btnRemoverCondutor.setVisibility(View.VISIBLE);

            btnExcluirVeiculo.setVisibility(View.GONE);
            btnAdicionarCondutor.setVisibility(View.GONE);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDialogAddEditCondutor(Veiculo veiculo) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_edit_condutor, null);
        Spinner spinnerCondutores = dialogView.findViewById(R.id.spinner_condutores);
        Button btnConfirmarOperacao = dialogView.findViewById(R.id.btn_confirmar_operacao);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        ViewModelCondutor viewModelCondutor = new ViewModelProvider(this).get(ViewModelCondutor.class);

        viewModelCondutor.getCondutores().observe(this, condutoresList -> {
            ArrayAdapter<Condutor> adapter = new ArrayAdapter<Condutor>(this, android.R.layout.simple_spinner_item, condutoresList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCondutores.setAdapter(adapter);
        });

        btnConfirmarOperacao.setOnClickListener(v -> {
            Condutor condutorSelecionado = (Condutor) spinnerCondutores.getSelectedItem();

            if (condutorSelecionado != null) {

                new AlertDialog.Builder(this).setTitle("Confirmar operação")
                        .setMessage("Deseja realmente alterar o condutor?")
                        .setPositiveButton("Sim", (dialog2, which) -> {
                            veiculo.setCondutorId(condutorSelecionado.getId());

                            ViewModelVeiculo viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);
                            viewModelVeiculo.update(veiculo);

                            String nome = condutorSelecionado.getPrimeiroNome() + " " + condutorSelecionado.getUltimoNome();

                            txtCondutor.setText(nome);
                            txtDocumentoCondutor.setText(this.formatarCpf(condutorSelecionado.getCpf()));

                            btnAdicionarCondutor.setVisibility(View.GONE);
                            btnExcluirVeiculo.setVisibility(View.GONE);
                            btnEditarCondutor.setVisibility(View.VISIBLE);
                            btnRemoverCondutor.setVisibility(View.VISIBLE);
                        }).setNegativeButton("Não", (dialog2, which) -> {
                            dialog2.dismiss();
                        }).show();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void removerCondutorVinculado(Veiculo veiculo) {

        new AlertDialog.Builder(this).setTitle("Confirmar operação")
                .setMessage("Deseja realmente remover o condutor?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    veiculo.setCondutorId(null);

                    ViewModelVeiculo viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);
                    viewModelVeiculo.update(veiculo);

                    txtCondutor.setText("--");
                    txtDocumentoCondutor.setText("--");

                    btnAdicionarCondutor.setVisibility(View.VISIBLE);
                    btnExcluirVeiculo.setVisibility(View.VISIBLE);
                    btnEditarCondutor.setVisibility(View.GONE);
                    btnRemoverCondutor.setVisibility(View.GONE);
                }).setNegativeButton("Não", (dialog, which) -> {
                    dialog.dismiss();
                }).show();

    }

    private void excluirVeiculo(Veiculo veiculo) {
        if (veiculo.getCondutorId() != null) {
            Toast.makeText(this, "Vocẽ não pode excluir um veículo com um condutor vinculado.", Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(this).setTitle("Confirmar operação")
                    .setMessage("Deseja realmente excluir o veículo?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        ViewModelVeiculo viewModelVeiculo = new ViewModelProvider(this).get(ViewModelVeiculo.class);

                        viewModelVeiculo.delete(veiculo);
                        Toast.makeText(this, "Veículo excluído com sucesso.", Toast.LENGTH_SHORT).show();
                        finish();
                    }).setNegativeButton("Não", (dialog, which) -> {
                        dialog.dismiss();
                    }).show();
        }
    }

    private String formatarCpf(String cpf) {
        if (cpf.length() == 11) {
            return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }

        return cpf;
    }
}