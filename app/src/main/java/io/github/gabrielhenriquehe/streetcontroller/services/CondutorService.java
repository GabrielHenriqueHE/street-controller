package io.github.gabrielhenriquehe.streetcontroller.services;

import androidx.lifecycle.LiveData;
import androidx.room.Transaction;

import java.util.Date;
import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.db.AppDatabase;
import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;

public abstract class CondutorService {

    private AppDatabase DB;

    public CondutorService(AppDatabase DB) {
        this.DB = DB;
    }

    @Transaction
    public void save(Condutor condutor) {
        DB.condutorDAO().save(condutor);
    }

    public LiveData<List<Condutor>> getAllCondutores() {
        return DB.condutorDAO().getAllCondutores();
    }
}
