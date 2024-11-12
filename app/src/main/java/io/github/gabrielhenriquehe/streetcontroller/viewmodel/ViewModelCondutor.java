package io.github.gabrielhenriquehe.streetcontroller.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.db.AppDatabase;
import io.github.gabrielhenriquehe.streetcontroller.db.dao.CondutorDAO;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;

public class ViewModelCondutor extends AndroidViewModel {

    private final LiveData<List<Condutor>> condutores;
    private final CondutorDAO condutorDAO;

    public ViewModelCondutor(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        condutorDAO = db.condutorDAO();
        condutores = condutorDAO.getAllCondutores();
    }

    public LiveData<List<Condutor>> getCondutores() {
        return condutores;
    }

    public void save(Condutor condutor) {
        new Thread(() -> condutorDAO.save(condutor)).start();
    }

    public void update(Condutor condutor) {
        new Thread(() -> condutorDAO.update(condutor)).start();
    }

    public void delete(Condutor condutor) {
        new Thread(() -> condutorDAO.delete(condutor)).start();
    }
}
