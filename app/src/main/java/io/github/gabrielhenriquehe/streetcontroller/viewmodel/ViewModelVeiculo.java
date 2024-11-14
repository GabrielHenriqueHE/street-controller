package io.github.gabrielhenriquehe.streetcontroller.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.db.AppDatabase;
import io.github.gabrielhenriquehe.streetcontroller.db.dao.VeiculoDAO;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;

public class ViewModelVeiculo extends AndroidViewModel {

    private final LiveData<List<Veiculo>> veiculos;
    private final VeiculoDAO veiculoDAO;

    public ViewModelVeiculo(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        veiculoDAO = db.veiculoDAO();
        veiculos = veiculoDAO.getAllVeiculos();
    }

    public LiveData<List<Veiculo>> getVeiculos() { return veiculos; };

    public void save(Veiculo veiculo) {
        new Thread(() -> {
            veiculoDAO.save(veiculo);
        }).start();
    }

    public void update(Veiculo veiculo) {
        new Thread(() -> {
            veiculoDAO.update(veiculo);
        }).start();
    }

    public void delete(Veiculo veiculo) {
        new Thread(() -> {
            veiculoDAO.delete(veiculo);
        }).start();
    }
}
