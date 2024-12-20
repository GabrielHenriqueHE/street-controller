package io.github.gabrielhenriquehe.streetcontroller.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

import io.github.gabrielhenriquehe.streetcontroller.entities.Veiculo;
import io.github.gabrielhenriquehe.streetcontroller.entities.VeiculoCondutor;

@Dao
public interface VeiculoDAO {
    @Insert
    void save(Veiculo veiculo);

    @Update
    void update(Veiculo veiculo);

    @Delete
    void delete(Veiculo veiculo);

    @Query("SELECT * FROM TB_veiculo")
    LiveData<List<Veiculo>> getAllVeiculos();

    @Query("SELECT * FROM TB_veiculo WHERE id = :id")
    Veiculo getVeiculoById(long id);

    @Query("SELECT * FROM TB_veiculo WHERE placa = :placa")
    Veiculo getVeiculoByPlaca(String placa);

    @Query("SELECT * FROM TB_veiculo WHERE condutorId = :condutorId")
    LiveData<List<VeiculoCondutor>> getVeiculosCondutor(long condutorId);

    @Query("SELECT * FROM TB_veiculo")
    LiveData<List<VeiculoCondutor>> getAllVeiculosComCondutor();
}
