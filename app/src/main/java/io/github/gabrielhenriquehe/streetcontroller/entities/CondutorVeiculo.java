package io.github.gabrielhenriquehe.streetcontroller.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CondutorVeiculo {

    @Embedded
    public Condutor condutor;

    @Relation(
            parentColumn = "id",
            entityColumn = "condutorId"
    )
    public List<Veiculo> veiculos;
}
