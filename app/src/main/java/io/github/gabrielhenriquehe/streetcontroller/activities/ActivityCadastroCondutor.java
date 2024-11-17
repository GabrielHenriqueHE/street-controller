package io.github.gabrielhenriquehe.streetcontroller.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.gabrielhenriquehe.streetcontroller.R;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.utils.Validator;
import io.github.gabrielhenriquehe.streetcontroller.viewmodel.ViewModelCondutor;

public class ActivityCadastroCondutor extends AppCompatActivity {

    private EditText txtCpf,txtPrimeiroNome, txtSegundoNome, dateDataNascimento, dateVencimentoCnh;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_condutor);

        txtCpf = findViewById(R.id.txt_cpf);
        txtPrimeiroNome = findViewById(R.id.txt_primeiro_nome);
        txtSegundoNome = findViewById(R.id.txt_segundo_nome);
        dateDataNascimento = findViewById(R.id.date_data_nascimento);
        dateVencimentoCnh = findViewById(R.id.date_vencimento_cnh);
        btnCadastrar = findViewById(R.id.btn_finalizar_cadastro);

        dateDataNascimento.setOnClickListener(v -> {
            this.showDatePickerDialog(v, dateDataNascimento);
        });

        dateVencimentoCnh.setOnClickListener(v -> {
            this.showDatePickerDialog(v, dateVencimentoCnh);
        });

        btnCadastrar.setOnClickListener(v -> {
            try {
                this.processRegister(v);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePickerDialog(View v, EditText edt) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                v.getContext(),
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    String dataEscolhida = String.format("%02d/%02d/%04d", selectedDayOfMonth, selectedMonth + 1, selectedYear);
                    edt.setText(dataEscolhida);
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void processRegister(View v) throws ParseException {
        String cpf, primeiroNome, segundoNome, dataNascimento, vencimentoCnh;

        cpf = txtCpf.getText().toString();

        primeiroNome = txtPrimeiroNome.getText().toString();
        segundoNome = txtSegundoNome.getText().toString();
        dataNascimento = dateDataNascimento.getText().toString();
        vencimentoCnh = dateVencimentoCnh.getText().toString();

        if (cpf.isEmpty() || primeiroNome.isEmpty() || segundoNome.isEmpty() || dataNascimento.isEmpty() || vencimentoCnh.isEmpty()) {
            Toast.makeText(v.getContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            
            if (!Validator.validarCpf(cpf)) {
                Toast.makeText(this, "CPF inválido.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Validator.validarNome(primeiroNome)) {
                Toast.makeText(this, "Primeiro nome inválido.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Validator.validarNome(segundoNome)) {
                Toast.makeText(this, "Segundo nome inválido.", Toast.LENGTH_SHORT).show();
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            if (!Validator.validarIdade(sdf.parse(dataNascimento))) {
                Toast.makeText(this, "Data de nascimento inválida.", Toast.LENGTH_SHORT).show();
                return;
            }
            
            Condutor condutor = new Condutor();
            condutor.setCpf(cpf);
            condutor.setPrimeiroNome(primeiroNome);
            condutor.setUltimoNome(segundoNome);

            condutor.setDataNascimento(sdf.parse(dataNascimento));
            condutor.setVencimentoHabilitacao(sdf.parse(vencimentoCnh));

            ViewModelCondutor viewModelCondutor = new ViewModelProvider(this).get(ViewModelCondutor.class);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                Condutor condutorExistente = viewModelCondutor.getCondutorByCpfSync(cpf);

                runOnUiThread(() -> {
                    if (condutorExistente != null) {
                        Toast.makeText(v.getContext(), "Já existe um condutor registrado usando esse CPF.", Toast.LENGTH_SHORT).show();
                    } else {
                        viewModelCondutor.save(condutor);
                        Toast.makeText(v.getContext(), "Condutor registrado com sucesso.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            });
        }
    }
}