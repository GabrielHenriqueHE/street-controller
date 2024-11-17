package io.github.gabrielhenriquehe.streetcontroller.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Transaction;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.db.AppDatabase;
import io.github.gabrielhenriquehe.streetcontroller.db.dao.CondutorDAO;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.CondutorVeiculo;

public class ViewModelCondutor extends AndroidViewModel {

    private final LiveData<List<Condutor>> condutores;
    private final LiveData<List<CondutorVeiculo>> condutoresComVeiculos;
    private final CondutorDAO condutorDAO;

    public ViewModelCondutor(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        condutorDAO = db.condutorDAO();
        condutores = condutorDAO.getAllCondutores();
        condutoresComVeiculos = condutorDAO.getAllCondutoresComVeiculo();
    }

    @Transaction
    public LiveData<List<Condutor>> getCondutores() {
        return condutores;
    }

    @Transaction
    public LiveData<List<CondutorVeiculo>> getCondutoresComVeiculos() {
        return condutoresComVeiculos;
    }

    @Transaction
    public LiveData<Condutor> getCondutorByCpf(String cpf) {
        return condutorDAO.getCondutorByCpf(cpf);
    }

    @Transaction
    public Condutor getCondutorByCpfSync(String cpf) {
        return condutorDAO.getCondutorByCpfSync(cpf);
    }

    @Transaction
    public void save(Condutor condutor) {
        new Thread(() -> condutorDAO.save(condutor)).start();
    }

    @Transaction
    public void update(Condutor condutor) {
        new Thread(() -> condutorDAO.update(condutor)).start();
    }

    @Transaction
    public void delete(Condutor condutor) {
        new Thread(() -> condutorDAO.delete(condutor)).start();
    }
}
