package io.github.gabrielhenriquehe.streetcontroller.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

public class VeiculoCondutor {

    @Embedded
    public Veiculo veiculo;

    @Relation(
            parentColumn = "condutorId",
            entityColumn = "id"
    )
    public Condutor condutor;
}
