package io.github.gabrielhenriquehe.streetcontroller.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.db.AppDatabase;
import io.github.gabrielhenriquehe.streetcontroller.db.dao.VeiculoDAO;
import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.entities.VeiculoCondutor;

public class ViewModelVeiculo extends AndroidViewModel {

    private final LiveData<List<VeiculoCondutor>> veiculosComCondutor;
    private final VeiculoDAO veiculoDAO;

    public ViewModelVeiculo(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        veiculoDAO = db.veiculoDAO();
        veiculosComCondutor = veiculoDAO.getAllVeiculosComCondutor();
    }

    public LiveData<List<VeiculoCondutor>> getVeiculosComCondutor() {
        return veiculosComCondutor;
    };

    public LiveData<List<VeiculoCondutor>> getVeiculosByCondutorId(long id) {
        return veiculoDAO.getVeiculosCondutor(id);
    }

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

    public Veiculo getVeiculoByPlacaSync(String placa) {
        return veiculoDAO.getVeiculoByPlaca(placa);
    }
}
