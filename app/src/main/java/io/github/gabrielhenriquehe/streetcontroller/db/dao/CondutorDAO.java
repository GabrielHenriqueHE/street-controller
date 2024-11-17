package io.github.gabrielhenriquehe.streetcontroller.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.entities.Condutor;
import io.github.gabrielhenriquehe.streetcontroller.entities.CondutorVeiculo;

@Dao
public interface CondutorDAO {

    @Insert
    void save(Condutor condutor);

    @Update
    void update(Condutor condutor);

    @Delete
    void delete(Condutor condutor);

    @Query("SELECT * FROM TB_condutor")
    LiveData<List<Condutor>> getAllCondutores();

    @Query("SELECT * FROM TB_condutor WHERE id = :id")
    Condutor getCondutorById(long id);

    @Query("SELECT * FROM TB_condutor WHERE cpf = :cpf")
    LiveData<Condutor> getCondutorByCpf(String cpf);

    @Query("SELECT * FROM TB_condutor WHERE cpf = :cpf")
    Condutor getCondutorByCpfSync(String cpf);

    @Query("SELECT * FROM TB_condutor WHERE id = :condutorId")
    CondutorVeiculo getCondutorVeiculo(long condutorId);

    @Query("SELECT * FROM TB_condutor")
    LiveData<List<CondutorVeiculo>> getAllCondutoresComVeiculo();
}
